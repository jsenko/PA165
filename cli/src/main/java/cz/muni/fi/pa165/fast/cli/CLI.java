package cz.muni.fi.pa165.fast.cli;

import com.sun.jersey.api.client.WebResource;

import cz.muni.fi.pa165.fast.service.TeamRestService;

/**
 * Hello world!
 *
 */
public class CLI 
{
    public static final String HELP_INFO = "Run the program with no arguments to display help page.";
    
    // this resource is set to .../rest path.
    //public static WebResource resource;
    
    public static TeamRestService teamService;
    
    public static void main(String[] args)
    {
        try
        {
            Command c = new MainCommand();
            for(int i = 0; i < args.length; i++)
            {
                if(args[i].startsWith("--"))
                {
                    //argument
                    
                    if(i+1<args.length && !args[i+1].startsWith("--"))
                    {
                        c = c.argument(args[i].substring(2), args[i+1]); // with value
                        i++;
                    }else
                    {
                        c = c.argument(args[i].substring(2), null); // without value
                    }
                }
                else
                {
                    //command
                    c = c.subCommand(args[i]);
                }
            }
            //execute
            c.execute();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Unknown error has ocured. Try again.");
        }
    }
}
