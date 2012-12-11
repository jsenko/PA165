package cz.muni.fi.pa165.fast.cli;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import cz.muni.fi.pa165.fast.service.TeamRestService;

public class MainCommand implements Command
{

    
    @Override
    public Command subCommand(String sc)
    {
        if("team".equals(sc))
        {
            return new TeamCommand();
        }
        else if("player".equals(sc))
        {
            throw new IllegalArgumentException("TODO");
        }
        else if("help".equals(sc))
        {
            execute();
            return null;
        }
        else
        {
            throw new UnknownCommandException(sc);   
        }
    }

    @Override
    public Command argument(String name, String value)
    {
        if("uri".equals(name))
        {
            try
            {
                Client client = new Client();
                WebResource resource = client.resource(new URI(value));
                CLI.teamService = new TeamRestService(resource.path("team"));
                
            }
            catch(URISyntaxException e)
            {
                throw new IllegalArgumentException("Invalid uri.", e);
            }
            return this;
        }
        else
        {
            throw new UnknownArgumentException(name);
        }
    }

    @Override
    public void execute()
    {
        // todo help
        String s = "FAST CLI using REST API\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available subcommands:\n"
                + " help - display this help page\n"
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
