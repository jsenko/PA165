package cz.muni.fi.pa165.fast.actionbean;

import javax.ejb.EJBException;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

import com.samaxes.stripejb3.EJBBean;

import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.security.SecurityFacade;

@UrlBinding("/users/{$event}")
public class UserActionBean implements ActionBean
{
    private ActionBeanContext context;
    
    private UserDTO userDTO;
    
    private boolean invalidLogin;
    
    private boolean loggedUser;
    
    @EJBBean("java:global/myapp/SecurityFacadeImpl!cz.muni.fi.pa165.fast.security.SecurityFacade")
    private SecurityFacade sf;
    
    @Before(stages = LifecycleStage.EventHandling)
    public void loadUser()
    {
        sf.setUser((User)context.getRequest().getSession().getAttribute("user"));
    }
    
    
    public void saveUser()
    {
        context.getRequest().getSession().setAttribute("user", sf.getUser());
    }
    
    public boolean isLoggedIn()
    {
        return sf.getCurrentLoggedInUser() != null;
    }
    
    @DefaultHandler
    public Resolution all()
    {
        
        return new ForwardResolution("/team/all.jsp");
    }
    
    public Resolution login()
    {
        return new ForwardResolution("/user/login.jsp");
    }

    public Resolution doLogin()
    {
        
        try{
            sf.login(userDTO.getLogin(), userDTO.getPassword());
            saveUser();
        }catch(EJBException ex)
        {
            if(ex.getCause() instanceof IllegalArgumentException)
            {
                
                invalidLogin = true;
                return new ForwardResolution("/user/login.jsp");
                
            }
            
            if(ex.getCause() instanceof IllegalStateException)
            {
                loggedUser = true;
                return new ForwardResolution("/user/login.jsp");
            }
            
            
        }
        
        return new ForwardResolution("/index.jsp");
    }
    
    
    public Resolution logout()
    {
        return new ForwardResolution("/user/logout.jsp");
    }
    
    public Resolution doLogout()
    {
        sf.logout();
        saveUser();
        return new ForwardResolution("/index.jsp");
    }
    

    @Override
    public void setContext(ActionBeanContext context) {
        
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    
    public boolean getInvalidLogin()
    {
        if(invalidLogin)
        {
            invalidLogin = false;
            return true;
        }else
            return false;
    }
    
    public boolean getLoggedUser()
    {
        if(loggedUser)
        {
            loggedUser = false;
            return true;
        }else
            return false;
    }
    
    
}
