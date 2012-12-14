
package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.helpInfo;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;

/**
 *
 * @author Lauro
 */
public class PlayerCreateCommand implements Command{

    private PlayerDTO playerDto = new PlayerDTO();
    
    @Override
    public Command subCommand(String subCommand) {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {
        
        if("name".equals(name))
        {
            playerDto.setName(value);
            
            return this;
        }
        
        if("surname".equals(name))
        {
            playerDto.setSurname(value);
            
            return this;
        }
        
        if("age".equals(name))
        {
            playerDto.setAge(Integer.parseInt(value));
            
            return this;
        }
        
        if("height".equals(name))
        {
            playerDto.setHeight(Integer.parseInt(value));
            
            return this;
        }
        
        if("weight".equals(name))
        {
            playerDto.setWeight(Integer.parseInt(value));
            
            return this;
        }
        
        if("teamId".equals(name))
        {
            if(CLI.teamService.getById(Long.parseLong(value))==null)
            {
                //todo error when team does not exist
                
                return null;
            }
            
            playerDto.setTeamId(Long.parseLong(value));
            
            return this;
        }
        
        unknownArgument(name);
        return null;
        
    }

    @Override
    public void help() {
        String s = "HELP\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available arguments:\n"
                + " --name - (required) name of new player.\n"
                + " --surname - (required) surname of new player.\n"
                + " --age - (required) age of new player.\n"
                + " --weight - (required) weight of new player.\n"
                + " --height - (required) height of new player.\n"
                + " --teamId - (required) team that player belogs to.";
        System.out.println(s);
    }

    @Override
    public void execute() {
        try
        {
            CLI.playerService.create(playerDto);
            System.out.println("Player '"+playerDto.getName()+"' created successfully!");
        }
        catch(Exception e)
        {
            System.out.println("Could not create a new team, an error has occurred.");
        }
    }
    
}
