package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.MatchGeneratorFacade;
import cz.muni.fi.pa165.fast.service.MatchService;
import cz.muni.fi.pa165.fast.service.TeamService;
import cz.muni.fi.pa165.fast.service.impl.MatchServiceImpl;
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

/**
 *
 * @author Jakub Senko
 *
 */
@UrlBinding("/matches/{$event}/{matchDTO.id}")
public class MatchActionBean implements ActionBean {

    private ActionBeanContext context;
    @EJBBean("java:global/myapp/MatchServiceImpl!cz.muni.fi.pa165.fast.service.MatchService")
    protected MatchService matchService;
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    @EJBBean("java:global/myapp/MatchGeneratorFacadeImpl!cz.muni.fi.pa165.fast.service.MatchGeneratorFacade")
    protected MatchGeneratorFacade facade;
    private String warning;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "round", required = true),
        @Validate(on = {"add", "save"}, field = "homeTeamId", required = true, minvalue = 1),
        @Validate(on = {"add", "save"}, field = "awayTeamId", required = true, minvalue = 1),
        @Validate(on = {"add", "save"}, field = "date", required = true)})
    private MatchDTO matchDTO;

    @EJBBean("java:global/myapp/SecurityFacadeImpl!cz.muni.fi.pa165.fast.security.SecurityFacade")
    private SecurityFacade sf;
    
    @Before(stages = LifecycleStage.EventHandling)
    private void loadUser()
    {
        sf.setUser((UserDTO)context.getRequest().getSession().getAttribute("user"));
    }
    
    @DefaultHandler
    public Resolution all() {

        return new ForwardResolution("/index.jsp");
    }

    public List<MatchDTO> getMatches() {
        return matchService.findAll();
    }

    public int getRounds() {
        List<MatchDTO> allMatches = matchService.findAll();
        int maxRound = 0;
        for (MatchDTO match : allMatches) {
            if (maxRound < match.getRound()) {
                maxRound = match.getRound();
            }
        }

        return maxRound;
    }

    public String getWarning() {
        return warning;
    }

    public List<TeamDTO> getTeams() {
        return teamService.findAll();
    }

    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadMatchFromDatabase() {

        String ids = context.getRequest().getParameter("matchDTO.id");
        if (ids == null) {
            return;
        }

        matchDTO = matchService.getById(Long.parseLong(ids));
    }

    public Resolution generate() {
        int teamsAmount = teamService.findAll().size();
        if ((teamsAmount == 0) || (teamsAmount % 2 != 0)) {
            this.warning = "You have wrong amount of teams, the amount must be event and higher than 0";
        } else {
            facade.drop();
            facade.generateMatches();
        }

        return new ForwardResolution(this.getClass(), "all");
    }

    public Resolution create() {
        return new ForwardResolution("/match/create.jsp");
    }

    public Resolution add() {
        matchService.create(matchDTO);

        return new ForwardResolution(this.getClass(), "all");
    }

    public Resolution edit() {
        return new RedirectResolution("/match/edit.jsp?matchDTO.id=" + matchDTO.getId());
    }

    public Resolution save() {
        matchService.update(matchDTO);

        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution delete() {
        matchService.delete(matchDTO);

        return new RedirectResolution(this.getClass(), "all");
    }

    public MatchDTO getMatchDTO() {
        return matchDTO;
    }

    public void setMatchDTO(MatchDTO matchDTO) {
        this.matchDTO = matchDTO;
    }

    @ValidationMethod(on = {"add", "save"})
    public void checkTeams(ValidationErrors errors) {
        if (matchDTO.getAwayTeamId() == matchDTO.getHomeTeamId()) {
            errors = getContext().getValidationErrors();

            ValidationError error = new LocalizableError("validation.match.sameTeamId");
            errors.addGlobalError(error);
        }
    }

    public boolean getCanCreate() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(MatchServiceImpl.class
                .getDeclaredMethod("create", MatchDTO.class));
    }
    
    public boolean getCanUpdate() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(MatchServiceImpl.class
                .getDeclaredMethod("update", MatchDTO.class));
    }
    
    public boolean getCanDelete() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(MatchServiceImpl.class
                .getDeclaredMethod("delete", MatchDTO.class));
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
