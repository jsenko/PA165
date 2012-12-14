package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.helpInfo;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;

/**
 *
 * @author Lauro
 */
public class PlayerDeleteCommand implements Command{

    private PlayerDTO playerDto = new PlayerDTO();
    
    @Override
    public Command subCommand(String subCommand) {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {
        if("id".equals(name))
        {
            try
            {
                playerDto.setId(Long.parseLong(value));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid id " + value + "'. Must be an integer.");
                return null;
            }
            return this;
        }
        
        unknownArgument(name);
        
        return null;
    }

    @Override
    public void help() {
        String s = "HELP\n"
                + "Available arguments:\n"
                + " --id - (required) id of a player to delete.";
        System.out.println(s);
    }

    @Override
    public void execute() {
        try
        {
            CLI.playerService.delete(playerDto);
            System.out.println("Player "+ playerDto.getId() + " deleted successfully!");
        }
        catch(Exception e)
        {
            System.out.println("Could not delete player, an error has occurred.");
        }
    }
    
}
