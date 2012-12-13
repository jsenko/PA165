package cz.muni.fi.pa165.fast.cli;

/**
 * Command interface enables command chaining
 * by defining a fluid interface. Each parsed subcommand
 * or argument results in a (optional) change of actual command object.
 * 
 * @author Jakub Senko
 *
 * If any of the methods returns null, execution is aborted.
 * This should be used only to handle errors.
 * 
 * TODO in next version, use reflection to call methods based on token directly, eliminating boilerplate code.
 */
public interface Command
{
    public Command subCommand(String subCommand);
    
    public Command argument(String name, String value);
    
    /**
     * This method is called whenever help command
     * or --help argument is encountered. Parsing of
     * subsequent commands is aborted.
     */
    public void help();
    
    /**
     * Execute the command.
     */
    public void execute();
}
