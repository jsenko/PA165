package cz.muni.fi.pa165.fast.actionbean;

import java.util.ArrayList;
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

import com.samaxes.stripejb3.EJBBean;

import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.GoalService;
import cz.muni.fi.pa165.fast.service.MatchService;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;

/**
 * 
 * @author Jakub Senko
 *
 */
@UrlBinding("/goals/{$event}")
public class GoalActionBean implements ActionBean{
    
    private ActionBeanContext context;
    
    @EJBBean("java:global/myapp/MatchServiceImpl!cz.muni.fi.pa165.fast.service.MatchService")
    protected MatchService matchService;
    @EJBBean("java:global/myapp/GoalServiceImpl!cz.muni.fi.pa165.fast.service.GoalService")
    protected GoalService goalService;
    @EJBBean("java:global/myapp/PlayerServiceImpl!cz.muni.fi.pa165.fast.service.PlayerService")
    protected PlayerService playerService;
    //@EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    //protected TeamService teamService;
    
    
    private GoalDTO goalDTO; //todo process date
    private Long matchId;
    
    @DefaultHandler
    public Resolution all() {

        return new ForwardResolution("/goal/all.jsp?matchId="+matchId);
    }
    
    public List<GoalDTO> getGoals()
    {
        return goalService.findByMatch(matchId.longValue());
    }
    public List<MatchDTO> getMatches() {
        return matchService.findAll();
    }
    public List<PlayerDTO> getPlayers() {


        return playerService.findAll(PlayerOrderBy.NAME);
    }
    public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	@Before(stages = LifecycleStage.BindingAndValidation)
    public void loadGoalFromDatabase() {
    	
        String ids = context.getRequest().getParameter("goalDTO.id");
        if (ids == null) {
            return;
        }
        
        
        goalDTO = goalService.getById(Long.parseLong(ids));

    }
    
    public Resolution create()
    {
    	return new ForwardResolution("/goal/create.jsp?matchId="+matchId);
    }

    
    public Resolution add()
    {
    
    	
    	goalService.create(goalDTO);
    	System.out.println(goalDTO);
        return new ForwardResolution(this.getClass(), "all");
    }

    
    public Resolution delete() {
    	goalService.delete(goalDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

    
    public Resolution edit() {

        return new RedirectResolution("/goal/edit.jsp?matchId="+matchId + "&goalDTO.id="+goalDTO.getId());
    }
    
    public Resolution save() {
    	
    	goalService.update(goalDTO);
        return new RedirectResolution(this.getClass(), "all");
    }

	public GoalDTO getGoalDTO() {
		return goalDTO;
	}

	public void setGoalDTO(GoalDTO goalDTO) {
		
		this.goalDTO = goalDTO;
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
