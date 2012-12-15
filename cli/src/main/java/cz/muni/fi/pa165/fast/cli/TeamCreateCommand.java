package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;
import cz.muni.fi.pa165.fast.dto.TeamDTO;

public class TeamCreateCommand implements Command {

    private TeamDTO teamDTO = new TeamDTO();

    @Override
    public Command subCommand(String subCommand) {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {
        if ("name".equals(name)) {
            if (value.isEmpty()) {
                System.out.println("Team has to have a name");
                return null;
            }
            teamDTO.setName(value);
            return this;
        }

        unknownArgument(name);
        return null;
    }

    @Override
    public void execute() {
        try {
            CLI.teamService.create(teamDTO);
            System.out.println("Team '" + teamDTO.getName() + "' created successfully!");
        } catch (Exception e) {
            System.out.println("Could not create a new team, an error has occurred.");
        }
    }

    @Override
    public void help() {
        String s = "HELP\n"
                + "Usage: [command] [--argument] [value] [subcommand] ...\n"
                + "Available arguments:\n"
                + " --name - (required) name of the new team.";
        System.out.println(s);
    }
}
