package cz.muni.fi.pa165.fast.cli;

import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author Jakub Senko
 *
 */
public class TeamCommand implements Command
{
    @Override
    public Command subCommand(String sc)
    {
        if("select".equals(sc))
        {
            return new TeamSelectCommand();
        }
        else if("create".equals(sc))
        {
            return new TeamCreateCommand();
        }
        else if("update".equals(sc))
        {
            return new TeamUpdateCommand();
        }
        else if("delete".equals(sc))
        {
            return new TeamDeleteCommand();
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
        // player does not accept any arguments
        throw new UnknownArgumentException(name);
    }

    @Override
    public void execute()
    {
        // no-arguments, display help
        String s = "FAST CLI using REST API - team command\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available subcommands:\n"
                + " help - display this help page\n"
                + " create - create new team\n"
                + " select - display table of teams\n"
                + " update - update team\n"
                + " delete - delete team\n"
                + "Available arguments:\n"
                + " none\n";
        System.out.println(s);
    }
}
