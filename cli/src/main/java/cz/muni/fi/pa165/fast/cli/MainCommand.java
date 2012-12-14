package cz.muni.fi.pa165.fast.cli;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import cz.muni.fi.pa165.fast.service.TeamRestService;
import static cz.muni.fi.pa165.fast.cli.CLI.*;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
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
