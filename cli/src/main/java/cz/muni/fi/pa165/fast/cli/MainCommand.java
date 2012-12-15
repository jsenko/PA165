package cz.muni.fi.pa165.fast.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import cz.muni.fi.pa165.fast.service.TeamRestService;
import static cz.muni.fi.pa165.fast.cli.CLI.*;
import cz.muni.fi.pa165.fast.service.PlayerRestService;

/** 
 * @author Jakub Senko
 */
public class MainCommand implements Command
{

    @Override
    public Command subCommand(String sc)
    {
        if(CLI.teamService == null)
        {
            System.out.println("Connection uri is required. Use '--uri [uri]'.");
            return null;
        }
        
        if("team".equals(sc))
        {
            return new TeamCommand();
        }
        
        if("player".equals(sc))
        {
            return new PlayerCommand();
        }
        
        if("interactive".equals(sc))
        {
            if(CLI.teamService.findAll()==null){
                System.out.println("Could not connect to REST service");
                return null;
            }
            

            
            String line = ""; // Line read from standard in
            System.out.println("Interactive CLI. (type 'help' for info, 'quit' to exit):");
            
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            while (!(line.equals("quit")))
            {
                System.out.print("> ");
                try
                {
                    line = in.readLine();
                }
                catch(IOException e)
                {
                    System.out.println("An unknown error has occured.");
                    return null;
                }
               
                if (!(line.equals("quit")))
                {
                    String delims = "[ ]+";
                    String[] args0 = line.split(delims);
                    // process double quotes
                    List<String> arglist = new ArrayList<String>();
                    for(int i = 0; i < args0.length; i++)
                    {
                        String s = args0[i];
                        if(args0[i].startsWith("\""))
                        {
                            //check if the token starts and ends with the double quotes
                            if(args0[i].endsWith("\""))
                            {
                                s = args0[i].substring(1, args0[i].length() -1);
                            }
                            else
                            {
                            
                                // add first part, without opening quote
                                s = args0[i].substring(1) + " ";
                                i++;
                                // while not found closing quote
                                while(!args0[i].endsWith("\""))
                                {
                                    s =  s + args0[i] + " ";
                                    i++;
                                }
                                // add final part with closing quote
                                s = s + args0[i].substring(0, args0[i].length() -1);
                            }
                        }
                        arglist.add(s);
                    }
                    String[] args = new String[arglist.size()];
                    main(arglist.toArray(args));
                }
            }
            return null;
        }

        unknownCommand(sc);
        return null; // abort
    }

    @Override
    public Command argument(String name, String value)
    {
        if("uri".equals(name))
        {
            try
            {
                // setup services using --uri
                Client client = new Client();
                WebResource resource = client.resource(new URI(value));
                CLI.teamService = new TeamRestService(resource.path("team"));
                CLI.playerService = new PlayerRestService(resource.path("player"));
                return this;
            }
            catch(Exception e)
            {
                System.out.println("Invalid uri '" + value + "'.");
                return null; //abort
            }
        }

        unknownArgument(name);
        return null; //abort
    }

    @Override
    public void execute()
    {
        helpInfo();
    }

    @Override
    public void help()
    {
        String s = "FAST CLI using REST API\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available subcommands:\n"
                + " player - display, create and edit players\n"
                + " team - display, create and edit teams\n"
                + "Available arguments:\n"
                + " --uri - (required) uri of the rest service\n"
                + "Example:\n"
                + " [java -jar ...] --uri http://localhost:8080/example/rest team help\n"
                + "  display help for team subcommand (available for all subcomands)";
        System.out.println(s);
    }
}
