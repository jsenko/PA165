package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.*;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class PlayerSelectCommand implements Command {

    private Long teamId;
    private Long id;

    @Override
    public Command subCommand(String subCommand) {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {
        if ("teamId".equals(name)) {
            if (id != null) {
                System.out.println("Argument '--teamId' cannot be used together with '--id'");
                return null;
            }

            try {
                teamId = Long.parseLong(value);
            } catch (Exception e) {
                System.out.println("Invalid teamId '"
                        + value + "'. Must be an integer.");
                return null;
            }
            return this;
        }

        if ("id".equals(name)) {
            if (teamId != null) {
                System.out.println("Argument '--id' cannot be used together with '--teamId'");
                return null;
            }

            try {
                id = Long.parseLong(value);
            } catch (Exception e) {
                System.out.println("Invalid id '"
                        + value + "'. Must be an integer.");
                return null;
            }
            return this;
        }

        unknownCommand(name);
        return null;
    }

    @Override
    public void help() {
        String s = "FAST CLI using REST API - player select command\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available arguments (one of them is required):\n"
                + " --id - display player with the specified id\n"
                + " --teamId - display players from team with the specified id\n";
        System.out.println(s);
    }

    private void so(char c) {
        System.out.print(c);
    }

    public void line(int[] size) {
        for (int k = 0; k < size.length; k++) {
            so('+');
            for (int i = 0; i < size[k] + 2; i++) {
                so('-');
            }
        }

        so('+');


        so('\n');
    }

    @Override
    public void execute() {

        List<PlayerDTO> list;


        if (id != null) {
            PlayerDTO dto = CLI.playerService.getById(id);
            if (dto == null) {
                System.out.println("Failed to retrieve data from rest service.\n"
                        + "Player with id '" + id + "' might not exist or bad uri.");
                return;
            }

            list = new ArrayList<PlayerDTO>();
            list.add(dto);
        } else if (teamId != null) {
            list = CLI.playerService.findPlayersByTeam(teamId, PlayerOrderBy.NAME);
            if (list == null) {
                System.out.println("Failed to retrieve data from rest service.\n"
                        + "Team with Id '" + teamId + "' might not exist or bad uri.");
                return;
            }
        } else {
            help();
            return;
        }


        int[] size = new int[]{/* id */2, /* name */ 4, /* surname */ 7, /* age */ 3, /* team */ 4, /* goals */ 5, /*assists*/ 7};


        for (PlayerDTO dto : list) {
            int x = Long.valueOf(dto.getId()).toString().length();
            if (x > size[0]) {
                size[0] = x;
            }

            x = dto.getName().length();
            if (x > size[1]) {
                size[1] = x;
            }

            x = dto.getSurname().length();
            if (x > size[2]) {
                size[2] = x;
            }

            x = Long.valueOf(dto.getAge()).toString().length();
            if (x > size[3]) {
                size[3] = x;
            }

            x = dto.getTeamName().length();
            if (x > size[4]) {
                size[4] = x;
            }

            x = Long.valueOf(dto.getGoals()).toString().length();
            if (x > size[5]) {
                size[5] = x;
            }

            x = Long.valueOf(dto.getAssists()).toString().length();
            if (x > size[6]) {
                size[6] = x;
            }
        }


        line(size);

        System.out.printf("| %" + size[0] + "s | %" + size[1] + "s "
                + "| %" + size[2] + "s "
                + "| %" + size[3] + "s "
                + "| %" + size[4] + "s "
                + "| %" + size[5] + "s "
                + "| %" + size[6] + "s |\n",
                "id", "name", "surname", "age", "team", "goals", "assists");

        line(size);

        for (PlayerDTO dto : list) {
            System.out.printf("| %" + size[0] + "s | %" + size[1] + "s "
                    + "| %" + size[2] + "s "
                    + "| %" + size[3] + "s "
                    + "| %" + size[4] + "s "
                    + "| %" + size[5] + "s "
                    + "| %" + size[6] + "s |\n",
                    Long.valueOf(dto.getId()).toString(),
                    dto.getName(),
                    dto.getSurname(),
                    Long.valueOf(dto.getAge()).toString(),
                    dto.getTeamName(),
                    Long.valueOf(dto.getGoals()).toString(),
                    Long.valueOf(dto.getAssists()).toString());
        }
        
        line(size);


    }
}
