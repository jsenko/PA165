package cz.muni.fi.pa165.fast.cli;

/**
 * 
 * @author Jakub Senko
 *
 */
public class UnknownArgumentException extends IllegalArgumentException
{
    public UnknownArgumentException(String argumentName)
    {
        super("Unknown argument '" + argumentName + "'.\n" + CLI.HELP_INFO);
    }
}
