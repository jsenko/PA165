package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.helpInfo;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;

/**
 *
 * @author Lauro
 */
public class PlayerCommand implements Command{

    @Override
    public Command subCommand(String subCommand) 
    {
        if("select".equals(subCommand))
        {
            return new PlayerSelectCommand();
        }
        
        if("create".equals(subCommand))
        {
            return new PlayerCreateCommand();
        }
        
        if("update".equals(subCommand))
        {
            return new PlayerUpdateCommand();
        }
        
        if("delete".equals(subCommand))
        {
            return new PlayerDeleteCommand();
        }

        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {
        unknownArgument(name);
        return null;
    }

    @Override
    public void help() {
        String s = "FAST CLI using REST API - player command\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available subcommands:\n"
                + " create - create new player\n"
                + " select - display table of players\n"
                + " update - update player\n"
                + " delete - delete player";
        System.out.println(s);
    }

    @Override
    public void execute() {
        helpInfo();
    }
    
}
