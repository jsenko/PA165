package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.UserService;
import cz.muni.fi.pa165.fast.service.impl.UserServiceImpl;
import java.util.List;
import javax.ejb.EJBException;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.apache.commons.codec.digest.DigestUtils;



@UrlBinding("/users/{$event}")
public class UserActionBean implements ActionBean
{
    private ActionBeanContext context;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add"}, field = "login", required = true),
        @Validate(on = {"add"}, field = "password", required = true)
    })
    private UserDTO userDTO;
    
    private boolean invalidLogin;
    
    private boolean loggedUser;
    
    @EJBBean("java:global/myapp/SecurityFacadeImpl!cz.muni.fi.pa165.fast.security.SecurityFacade")
    private SecurityFacade sf;
    @EJBBean("java:global/myapp/UserServiceImpl!cz.muni.fi.pa165.fast.service.UserService")
    protected UserService userService;
    
    @Before(stages = LifecycleStage.EventHandling)
    public void loadUser()
    {
        sf.setUser((UserDTO)context.getRequest().getSession().getAttribute("user"));
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
        return new ForwardResolution("/user/all.jsp");
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
            else if(ex.getCause() instanceof IllegalStateException)
            {
                loggedUser = true;
                return new ForwardResolution("/user/login.jsp");
            }
            else
                throw ex;
            
            
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
    
    public List<UserDTO> getUsers() {
        return userService.findAll();
    }
    
    public boolean getCanDelete() throws NoSuchMethodException, SecurityException
    {
        return sf.authorize(UserServiceImpl.class
                .getDeclaredMethod("delete", UserDTO.class));
    }
    
    public boolean getCanCreate() throws NoSuchMethodException, SecurityException
    {
        return sf.authorize(UserServiceImpl.class
                .getDeclaredMethod("create", UserDTO.class));
    }
    
    public Resolution delete() {
        userService.delete(userDTO);
        return new RedirectResolution(this.getClass(), "all");
    }
    
    public Resolution add() {
        //if user already exists
        if(userService.getByLogin(userDTO.getLogin()) != null)
        {
            invalidLogin = true;
            return new ForwardResolution("/user/create.jsp");
        }
        
        userDTO.setPassword(DigestUtils.sha256Hex(userDTO.getPassword()));
        try{
            userService.create(userDTO);
        }catch(Exception ex)
        {
            // something bad has happened, ignore and log
        }
        return new RedirectResolution(this.getClass(), "all");
    }
    
    public Resolution create() {
        return new ForwardResolution("/user/create.jsp");
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
        }else {
            return false;
        }
    }
    
    
}
