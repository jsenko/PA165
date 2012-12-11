package cz.muni.fi.pa165.fast.cli;

/**
 * 
 * @author Jakub Senko
 *
 */
public interface Command
{
    public Command subCommand(String subCommand);
    
    public Command argument(String name, String value);
    
    public void execute();
}
