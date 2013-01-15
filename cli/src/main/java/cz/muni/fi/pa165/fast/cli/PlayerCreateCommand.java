package cz.muni.fi.pa165.fast.cli;

import static cz.muni.fi.pa165.fast.cli.CLI.unknownArgument;
import static cz.muni.fi.pa165.fast.cli.CLI.unknownCommand;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;

/**
 *
 * @author Lauro
 */
public class PlayerCreateCommand implements Command {

    private PlayerDTO playerDto = new PlayerDTO();

    @Override
    public Command subCommand(String subCommand) {
        unknownCommand(subCommand);
        return null;
    }

    @Override
    public Command argument(String name, String value) {

        if ("name".equals(name)) {
            if (value.isEmpty()) {
                System.out.println("Player has to have a name");
                return null;
            }
            playerDto.setName(value);

            return this;
        }

        if ("surname".equals(name)) {
            if (value.isEmpty()) {
                System.out.println("Player has to have a surname");
                return null;
            }
            playerDto.setSurname(value);

            return this;
        }

        if ("age".equals(name)) {
            try {
                if (Integer.parseInt(value) < 16) {
                    System.out.println("Player has to be older than 15 years.");
                    return null;
                }
                playerDto.setAge(Integer.parseInt(value));

            } catch (NumberFormatException ex) {
                System.out.println("Invalid age " + value + ". Must be an integer.");
            }

            return this;
        }

        if ("height".equals(name)) {
            try {
                if (Integer.parseInt(value) < 100) {
                    System.out.println("Player has to have at least 100cm.");
                    return null;
                }
                playerDto.setHeight(Integer.parseInt(value));

            } catch (NumberFormatException ex) {
                System.out.println("Invalid height " + value + ". Must be an integer.");
            }

            return this;
        }

        if ("weight".equals(name)) {
            try {
                if (Integer.parseInt(value) < 50) {
                    System.out.println("Player has to have at least 50kg.");
                    return null;
                }
                playerDto.setWeight(Integer.parseInt(value));

            } catch (NumberFormatException ex) {
                System.out.println("Invalid weight " + value + ". Must be an integer.");
            }

            return this;
        }

        if ("teamId".equals(name)) {
            try {
                if (CLI.teamService.getById(Long.parseLong(value)) == null) {
                    System.out.println("Team with id '" + value + "' might not exist.");

                    return null;
                }

                playerDto.setTeamId(Long.parseLong(value));

            } catch (NumberFormatException ex) {
                System.out.println("Invalid id " + value + ". Must be an integer.");
            }

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
        try {
            if (playerDto.getAge() == 0 || playerDto.getHeight() == 0 || playerDto.getName() == null || playerDto.getSurname() == null || playerDto.getTeamId() == 0 || playerDto.getWeight() == 0) {
                System.out.println("Missing some required commands. Could not create new player");
                help();
                return;
            }

            CLI.playerService.create(playerDto);
            System.out.println("Player '" + playerDto.getName() + " " + playerDto.getSurname() + "' created successfully!");
        } catch (Exception e) {
            System.out.println("Could not create new player, an error has occurred.");
        }
    }
}
