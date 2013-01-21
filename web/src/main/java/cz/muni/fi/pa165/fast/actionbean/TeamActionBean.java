package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.MatchGeneratorFacade;
import cz.muni.fi.pa165.fast.service.TeamService;
import cz.muni.fi.pa165.fast.service.impl.TeamServiceImpl;

import java.util.List;

import javax.inject.Inject;

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

@UrlBinding("/teams/{$event}")
public class TeamActionBean implements ActionBean {

    private ActionBeanContext context;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private TeamDTO team;
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    @EJBBean("java:global/myapp/MatchGeneratorFacadeImpl!cz.muni.fi.pa165.fast.service.MatchGeneratorFacade")
    protected MatchGeneratorFacade facade;
    
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
    
    
    @DefaultHandler
    public Resolution all() {
        
        return new ForwardResolution("/team/all.jsp");
    }

    public Resolution add() {
        teamService.create(team);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution delete() {
        teamService.delete(team);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution generate() {
        teamService.generate();
        facade.generatePlayers();
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadTeamFromDatabase() {
        String ids = context.getRequest().getParameter("team.id");
        if (ids == null) {
            return;
        }
        team = teamService.getById(new Long(Integer.parseInt(ids)));
    }

    public Resolution edit() {
        return new ForwardResolution("/team/edit.jsp");
    }

    public Resolution save() {
        teamService.update(team);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution create() {
        return new ForwardResolution("/team/create.jsp");
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public List<TeamDTO> getTeams() {
        return teamService.findAll();
    }
    
    
    // allow access to create team
    public boolean getCanCreate() throws NoSuchMethodException, SecurityException
    {
        return sf.authorize(TeamServiceImpl.class
                .getDeclaredMethod("create", TeamDTO.class));
    }
    
    public boolean getCanUpdate() throws NoSuchMethodException, SecurityException
    {
        return sf.authorize(TeamServiceImpl.class
                .getDeclaredMethod("delete", TeamDTO.class));
    }
    
    public boolean getCanDelete() throws NoSuchMethodException, SecurityException
    {
        return sf.authorize(TeamServiceImpl.class
                .getDeclaredMethod("delete", TeamDTO.class));
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
