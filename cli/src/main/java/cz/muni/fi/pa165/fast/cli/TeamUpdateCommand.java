package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;
import cz.muni.fi.pa165.fast.dto.TeamDTO;

public class TeamUpdateCommand implements Command {

    private TeamDTO teamDTO = new TeamDTO();

    @Override
    public Command subCommand(String subCommand) {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {
        if ("name".equals(name)) {
            teamDTO.setName(value);
            return this;
        }

        if ("id".equals(name)) {
            try {
                teamDTO.setId(Long.parseLong(value));
            } catch (NumberFormatException e) {
                System.out.println("Invalid id '"
                        + value + "'. Must be an integer.");
                return null;
            }
            return this;
        }

        unknownArgument(name);
        return null;
    }

    @Override
    public void execute() {
        try {
            CLI.teamService.update(teamDTO);
            System.out.println("Team " + teamDTO.getId() + " updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not update the team, team with id '" + teamDTO.getId() + "' dowsn't exist.");
        } catch (Exception e) {
            System.out.println("Could not update the team, an error has occurred.");
        }
    }

    @Override
    public void help() {
        String s = "HELP\n"
                + "Available arguments:\n"
                + " --id - (required) id of the team to update.\n"
                + " --name - (required) new name of the team.";
        System.out.println(s);
    }
}
