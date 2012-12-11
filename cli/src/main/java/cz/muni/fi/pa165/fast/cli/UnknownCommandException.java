package cz.muni.fi.pa165.fast.cli;

/**
 * 
 * @author Jakub Senko
 *
 */
public class UnknownCommandException extends IllegalArgumentException
{
    public UnknownCommandException(String commandName)
    {
        super("Unknown command '" + commandName + "'.\n" + CLI.HELP_INFO);
    }
}
