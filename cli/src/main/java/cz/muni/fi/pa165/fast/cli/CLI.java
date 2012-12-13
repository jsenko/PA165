package cz.muni.fi.pa165.fast.cli;

import cz.muni.fi.pa165.fast.service.TeamRestService;

/**
 * Hello world!
 *
 */
public class CLI 
{
    public static TeamRestService teamService;
    
    public static void main(String[] args)
    {
        try
        {
            Command c = new MainCommand(); // default command
            for(int i = 0; i < args.length; i++)
            {
                if("help".equals(args[i]) || "--help".equals(args[i]))
                {
                    c.help();
                    break;
                }
                else if(args[i].startsWith("--"))
                {
                    String name = args[i].substring(2);
                    
                    //argument
                    if(i+1 < args.length && !args[i+1].startsWith("--")) // with value
                    {
                        c = c.argument(name, args[i+1]); 
                        i++;
                    }
                    else // without value
                    {
                        c = c.argument(name, null); 
                    }
                }
                else
                {
                    //command
                    c = c.subCommand(args[i]);
                }
                
                if(c == null)
                {
                    break; //abort
                }
                
                if(i == args.length - 1) // final token was parsed
                {
                    c.execute();
                }
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println("An unknown error has ocured. Check your connection and try again.");
        }
    }
    
    /**
     * Convenience method to display standardized message.
     */
    public static void helpInfo()
    {
        System.out.println("Run any command with '--help' argument "
                +"or 'help' subcommand to display help page.");
    }
    
    /**
     * Convenience method to display standardized error message.
     */
    public static void unknownCommand(String commandName)
    {
        System.out.println("Unknown command '" + commandName + "'.");
        helpInfo();
    }
    
    /**
     * Convenience method to display standardized error message.
     */
    public static void unknownArgument(String argumentName)
    {
        System.out.println("Unknown argument '" + argumentName + "'.");
        helpInfo();
    }
}
