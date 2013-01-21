package cz.muni.fi.pa165.fast.actionbean;

import java.util.List;

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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.samaxes.stripejb3.EJBBean;

import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.GoalService;
import cz.muni.fi.pa165.fast.service.MatchService;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import cz.muni.fi.pa165.fast.service.TeamService;
import cz.muni.fi.pa165.fast.service.impl.GoalServiceImpl;
/**
 *
 * @author Jakub Senko
 *
 */
@UrlBinding("/goals/{$event}")
public class GoalActionBean implements ActionBean {

    private ActionBeanContext context;
    @EJBBean("java:global/myapp/MatchServiceImpl!cz.muni.fi.pa165.fast.service.MatchService")
    protected MatchService matchService;
    @EJBBean("java:global/myapp/GoalServiceImpl!cz.muni.fi.pa165.fast.service.GoalService")
    protected GoalService goalService;
    @EJBBean("java:global/myapp/PlayerServiceImpl!cz.muni.fi.pa165.fast.service.PlayerService")
    protected PlayerService playerService;
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "scoredPlayerId", required = true, minvalue = 1),
        @Validate(on = {"add", "save"}, field = "assistPlayerId", required = true, minvalue = 1),
        @Validate(on = {"add", "save"}, field = "goalMinute", required = true, minvalue = 1)})
    private GoalDTO goalDTO;
    
    @EJBBean("java:global/myapp/SecurityFacadeImpl!cz.muni.fi.pa165.fast.security.SecurityFacade")
    private SecurityFacade sf;
    


    @DefaultHandler
    public Resolution all() {

        return new ForwardResolution("/goal/all.jsp?goalDTO.matchId=" + goalDTO.getMatchId());
    }

    public List<GoalDTO> getGoals() {
        return goalService.findByMatch(goalDTO.getMatchId());
    }

    public List<PlayerDTO> getPlayers() {
        TeamDTO t1 = teamService.getById(matchService.getById(goalDTO.getMatchId()).getAwayTeamId());
        TeamDTO t2 = teamService.getById(matchService.getById(goalDTO.getMatchId()).getHomeTeamId());
        List<PlayerDTO> matchPlayers = playerService.findPlayersByTeam(t1.getId(), PlayerOrderBy.NAME);
        matchPlayers.addAll(playerService.findPlayersByTeam(t2.getId(), PlayerOrderBy.NAME));
        return matchPlayers;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadGoalFromDatabase() {

        String ids = context.getRequest().getParameter("goalDTO.id");
        if (ids == null) {
            return;
        }

        goalDTO = goalService.getById(Long.parseLong(ids));
    }

    public Resolution create() {
        return new ForwardResolution("/goal/create.jsp?goalDTO.matchId=" + goalDTO.getMatchId());
    }

    public Resolution add() {
        goalService.create(goalDTO);

        return new ForwardResolution(this.getClass(), "all");
    }

    public Resolution delete() {
        goalService.delete(goalDTO);

        return new ForwardResolution(this.getClass(), "all");
    }

    public Resolution edit() {
        return new RedirectResolution("/goal/edit.jsp?goalDTO.matchId=" + goalDTO.getMatchId() + "&goalDTO.id=" + goalDTO.getId());
    }

    public Resolution save() {
        goalService.update(goalDTO);

        return new ForwardResolution(this.getClass(), "all");
    }

    public GoalDTO getGoalDTO() {
        return goalDTO;
    }

    public void setGoalDTO(GoalDTO goalDTO) {
        this.goalDTO = goalDTO;
    }

    @ValidationMethod(on = {"add", "save"})
    public void checkPlayers(ValidationErrors errors) {
        if (goalDTO.getAssistPlayerId() == goalDTO.getScoredPlayerId()) {
            errors = getContext().getValidationErrors();

            ValidationError error = new LocalizableError("validation.goal.samePlayerId");
            errors.addGlobalError(error);
        }

        if (teamService.findByPlayer(goalDTO.getAssistPlayerId()).getId() != teamService.findByPlayer(goalDTO.getScoredPlayerId()).getId()) {
            errors = getContext().getValidationErrors();

            ValidationError error = new LocalizableError("validation.goal.playersFromDiffTeams");
            errors.addGlobalError(error);
        }
    }

    public boolean getCanCreate() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(GoalServiceImpl.class
                .getDeclaredMethod("create", GoalDTO.class));
    }
    
    public boolean getCanUpdate() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(GoalServiceImpl.class
                .getDeclaredMethod("update", GoalDTO.class));
    }
    
    public boolean getCanDelete() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(GoalServiceImpl.class
                .getDeclaredMethod("delete", GoalDTO.class));
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
