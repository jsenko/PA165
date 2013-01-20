package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.MatchGeneratorFacade;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.sessions.Session;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@UrlBinding("/users/{$event}")
public class UserActionBean implements ActionBean
{
    private ActionBeanContext context;
    
    private UserDTO userDTO;
    
    @EJBBean("java:global/myapp/SecurityFacadeImpl!cz.muni.fi.pa165.fast.security.SecurityFacade")
    private SecurityFacade sf;
    
    @Before(stages = LifecycleStage.EventHandling)
    private void loadUser()
    {
        sf.setUser((User)context.getRequest().getSession().getAttribute("user"));
    }
    
    @After(stages = LifecycleStage.RequestComplete)
    private void saveUser()
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
        // TODO catch exceptions and display error message
        sf.login(userDTO.getLogin(), userDTO.getPassword());

        return new ForwardResolution("/team/all.jsp");
    }
    
    
    public Resolution logout()
    {
        return new ForwardResolution("/user/logout.jsp");
    }
    
    public Resolution doLogout()
    {
        sf.logout();

        return new ForwardResolution("/team/all.jsp");
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
    
    
}
