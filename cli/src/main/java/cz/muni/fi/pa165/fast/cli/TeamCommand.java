package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.helpInfo;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;

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
        
        if("create".equals(sc))
        {
            return new TeamCreateCommand();
        }
        
        if("update".equals(sc))
        {
            return new TeamUpdateCommand();
        }
        
        if("delete".equals(sc))
        {
            return new TeamDeleteCommand();
        }

        unknownCommand(sc);
        return null;
    }

    @Override
    public Command argument(String name, String value)
    {
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
        String s = "FAST CLI using REST API - team command\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available subcommands:\n"
                + " create - create new team\n"
                + " select - display table of teams\n"
                + " update - update team\n"
                + " delete - delete team";
        System.out.println(s);
    }
}
