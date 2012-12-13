package cz.muni.fi.pa165.fast.cli;

import java.util.ArrayList;
import java.util.List;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import static cz.muni.fi.pa165.fast.cli.CLI.*;

/**
 * 
 * @author Jakub Senko
 * 
 */
public class TeamSelectCommand implements Command
{
    private Long id;
    private boolean all = false;
    private Long playerId;
    
    @Override
    public Command subCommand(String subCommand)
    {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value)
    {
        if ("id".equals(name))
        {
            if(playerId != null)
            {
                System.out.println("Argument '--id' cannot be used together with '--playerId'");
                return null;
            }
            
            try
            {
                id = Long.parseLong(value);
            }
            catch(Exception e)
            {
                System.out.println("Invalid id '"
                        + value + "'. Must be an integer.");
                return null;
            }
            return this;
        }
        
        if("playerId".equals(name))
        {
            if(id != null)
            {
                System.out.println("Argument '--playerId' cannot be used together with '--id'");
                return null;
            }
            
            try
            {
                playerId = Long.parseLong(value);
            }
            catch(Exception e)
            {
                System.out.println("Invalid playerId '"
                        + value + "'. Must be an integer.");
                return null;
            }
            return this;
        }
        
        if("all".equals(name))
        {
            all = true;
            return this;
        }

        unknownCommand(name);
        return null;
    }
    
    private void so(char c)
    {
        System.out.print(c);
    }
    
    
    public void line(int[] size)
    {
        so('+');
        for(int i = 0; i < size[0] + 2; i++) so('-');
        so('+');
        for(int i = 0; i < size[1] + 2; i++) so('-');
        so('+');
        
        if(all)
        {
            for(int j = 0; j < 4; j++)
            {
                for(int i = 0; i < size[2] + 2; i++) so('-');
                so('+');
            }
            for(int i = 0; i < size[3] + 2; i++) so('-');
            so('+');
            for(int i = 0; i < size[4] + 2; i++) so('-');
            so('+');
        }
        so('\n');
    }
    

    @Override
    public void execute()
    {
        List<TeamDTO> list;
        
        
            if(id != null)
            {
                TeamDTO dto = CLI.teamService.getById(id);
                if(dto == null)
                {
                    System.out.println("Failed to retrieve data from rest service.\n"
                            + "Team with id '" + id + "' might not exist or bad uri.");
                    return;
                }
                
                list = new ArrayList<TeamDTO>();
                list.add(dto);
            }
            else if(playerId != null)
            {
                TeamDTO dto = CLI.teamService.findByPlayer(playerId);
                if(dto == null)
                {
                    System.out.println("Failed to retrieve data from rest service.\n"
                            + "Team with playerId '" + playerId + "' might not exist or bad uri.");
                    return;
                }
                
                list = new ArrayList<TeamDTO>();
                list.add(dto);
            }
            else
            {
                list = CLI.teamService.findAll();
                if(list == null)
                {
                    System.out.println("Failed to retrieve data from rest service. Check your uri.");
                    return;
                }
            }
        

        // determine size of columns    
        
        int[] size = new int[] {/* id */2, /* name */4, /* GP */2, /* SC */2, /* PTS */3};
        
        
        for(TeamDTO dto : list)
        {
            int x = Long.valueOf(dto.getId()).toString().length();
            if (x > size[0]) size[0] = x;

            x = dto.getName().length();
            if (x > size[1]) size[1] = x;
            
            if(all)
            {
                x = Long.valueOf(dto.getWon() + dto.getDraw() + dto.getLost()).toString().length();
                if (x > size[2]) size[2] = x;
                
                x = Long.valueOf(dto.getGoalsFor()).toString().length() + 
                        Long.valueOf(dto.getGoalsFor()).toString().length()
                        + 1 /* semicolon */;
                if (x > size[3]) size[3] = x;
            
                x = Long.valueOf(dto.getPoints()).toString().length();
                if (x > size[4]) size[4] = x;
            }
        }
        
        // ***** draw table
        // header
        
            line(size);
            
            

            if(all)
            {
                System.out.printf("| %" + size[0] + "s | %" + size[1] + "s "
                        + "| %" + size[2] + "s "
                        + "| %" + size[2] + "s "
                        + "| %" + size[2] + "s "
                        + "| %" + size[2] + "s "
                        + "| %" + size[3] + "s "
                        + "| %" + size[4] + "s |\n",
                        "id", "name", "GP", "W", "L", "T", "SC", "PTS");
            }
            else
            {
                System.out.printf("| %" + size[0] + "s | %" + size[1] + "s |\n", "id", "name");
            }

            line(size);

        // body
        for (TeamDTO dto : list)
        {
            if(all)
            {
                System.out.printf("| %" + size[0] + "s | %" + size[1] + "s "
                    + "| %" + size[2] + "s "
                    + "| %" + size[2] + "s "
                    + "| %" + size[2] + "s "
                    + "| %" + size[2] + "s "
                    + "| %" + size[3] + "s "
                    + "| %" + size[4] + "s |\n",
                    Long.valueOf(dto.getId()).toString(), dto.getName(), dto.getWon() + dto.getDraw() + dto.getLost(),
                    dto.getWon(), dto.getWon(), dto.getLost(), dto.getGoalsFor() + ":" + dto.getGoalsAgainst(), 
                    dto.getPoints());
            }
            else
            {
                System.out.printf("| %" + size[0] + "s | %" + size[1] + "s |\n",
                        Long.valueOf(dto.getId()).toString(), dto.getName());
            }
        }

        // footer
        line(size);
        
        System.out.println("Total " + list.size() + " row(s).");
    }

    @Override
    public void help()
    {
        String s = "FAST CLI using REST API - team select command\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available arguments:\n"
                + " --all - display extended table\n"
                + " --id - display team with the specified id\n"
                + " --playerId - display team member of which is a player with the  specified playerId";
        System.out.println(s);
    }

}
