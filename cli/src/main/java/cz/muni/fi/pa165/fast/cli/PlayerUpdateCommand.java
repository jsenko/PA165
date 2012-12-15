package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.helpInfo;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;

/**
 *
 * @author Lauro
 */
public class PlayerUpdateCommand implements Command{

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
                System.out.println("Invalid player id '" + value + "'. Must be an integer.");
                return null;
            }
            
            return this;
        }
        
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
            try
            {
                playerDto.setAge(Integer.parseInt(value));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid player age '" + value + "'. Must be an integer.");
                return null;
            }
            
            return this;
        }
        
        if("id".equals(name))
        {
            try
            {
                playerDto.setWeight(Integer.parseInt(value));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid player weight '" + value + "'. Must be an integer.");
                return null;
            }
            
            return this;
        }
        
        if("height".equals(name))
        {
            try
            {
                playerDto.setHeight(Integer.parseInt(value));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid player height '" + value + "'. Must be an integer.");
                return null;
            }
            
            return this;
        }
        
        if("teamId".equals(name))
        {
            try
            {
                if(CLI.teamService.getById(Long.parseLong(value)) == null)
                {
                    System.out.println("Team id '" + value + "' does not exist.");
                    return null;
                }
                
                playerDto.setTeamId(Long.parseLong(value));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid team id '" + value + "'. Must be an integer.");
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
                + " --id - (required) id of the team to update.\n"
                + " --name - new name of player.\n"
                + " --surname - new surname of player.\n"
                + " --age - new age of player.\n"
                + " --weight - new weight of player\n"
                + " --height - new height of player\n"
                + " --teamId - new team id of player";
        System.out.println(s);
    }

    @Override
    public void execute() {
        try
        {
                      
            PlayerDTO oldPlayer = CLI.playerService.getById(playerDto.getId());
            
            
            
            if(playerDto.getAge() != 0)
                oldPlayer.setAge(playerDto.getAge());
            
            
            if(playerDto.getHeight() != 0)
                oldPlayer.setHeight(playerDto.getHeight());
            
            
            if(playerDto.getName() != null)
                oldPlayer.setName(playerDto.getName());
            
            if(playerDto.getSurname() != null)
                oldPlayer.setSurname(playerDto.getSurname());
            
            if(playerDto.getTeamId() != 0)
                oldPlayer.setTeamId(playerDto.getTeamId());
            
            if(playerDto.getWeight() != 0)
                oldPlayer.setWeight(playerDto.getWeight());
            
            CLI.playerService.update(oldPlayer);
            System.out.println("Player "+playerDto.getId()+" updated successfully!");
        }
        catch(Exception e)
        {
            
            
            System.out.println("Could not update player, an error has occurred. Check if you provided required arguments.");
        }
    }
    
}
