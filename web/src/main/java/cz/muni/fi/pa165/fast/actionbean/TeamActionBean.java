package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.List;
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

@UrlBinding("/teams/{$event}")
public class TeamActionBean implements ActionBean{
    
    private ActionBeanContext context;
    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private TeamDTO team;
    
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    
    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/table.jsp");
    }
    
    public Resolution add() {
        teamService.create(team);
        return new RedirectResolution(this.getClass(), "all");
    }
    
    public Resolution delete() {
        teamService.delete(team);
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
        return new ForwardResolution("/edit/teamEdit.jsp");
    }
 
    public Resolution save() {
        teamService.update(team);
        return new RedirectResolution(this.getClass(), "all");
    }
    
    public TeamDTO getTeam(){
        return team;
    }
    
    public void setTeam(TeamDTO team){
        this.team = team;
    }
    
    public List<TeamDTO> getTeams() {
        return teamService.findAll();
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
