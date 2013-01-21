package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.actionbean.context.PlayerActionBeanContext;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.MatchGeneratorFacade;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import cz.muni.fi.pa165.fast.service.TeamService;
import cz.muni.fi.pa165.fast.service.impl.PlayerServiceImpl;
import java.util.ArrayList;
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
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@UrlBinding("/players/{$event}")
public class PlayerActionBean implements ActionBean {

    private PlayerActionBeanContext context;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true),
        @Validate(on = {"add", "save"}, field = "surname", required = true),
        @Validate(on = {"add", "save"}, field = "age", required = true, minvalue = 16),
        @Validate(on = {"add", "save"}, field = "height", required = true, minvalue = 100),
        @Validate(on = {"add", "save"}, field = "weight", required = true, minvalue = 50),})
    private PlayerDTO player;
    private TeamDTO team;
    private int order;
    @EJBBean("java:global/myapp/PlayerServiceImpl!cz.muni.fi.pa165.fast.service.PlayerService")
    protected PlayerService playerService;
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    @EJBBean("java:global/myapp/MatchGeneratorFacadeImpl!cz.muni.fi.pa165.fast.service.MatchGeneratorFacade")
    protected MatchGeneratorFacade facade;

    
    @EJBBean("java:global/myapp/SecurityFacadeImpl!cz.muni.fi.pa165.fast.security.SecurityFacade")
    private SecurityFacade sf;
    
    @Before(stages = LifecycleStage.EventHandling)
    private void loadUser()
    {
        sf.setUser((UserDTO)context.getRequest().getSession().getAttribute("user"));
    }
    
    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/player/all.jsp");
    }

    public Resolution selectTeam() {
        if (team != null && team.getId() != 0) {
            getContext().setSelectedTeam(team);
        }

        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution generate() {
        facade.generatePlayers();
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution add() {
        player.setTeamId(getContext().getSelectedTeam().getId());
        playerService.create(player);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution delete() {
        playerService.delete(player);
        return new RedirectResolution(this.getClass(), "all");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadPlayerFromDatabase() {
        String ids = context.getRequest().getParameter("player.id");
        if (ids == null) {
            return;
        }
        player = playerService.getById(new Long(Integer.parseInt(ids)));
    }

    public Resolution edit() {
        return new ForwardResolution("/player/edit.jsp");
    }

    public Resolution save() {
        playerService.update(player);
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution order() {
        getContext().setOrder(PlayerOrderBy.values()[order]);
        return new RedirectResolution(this.getClass(), "all");
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public List<PlayerDTO> getPlayers() {
        TeamDTO utTeam = getContext().getSelectedTeam();
        if (utTeam == null || utTeam.getId() == 0) {
            if (teamService.findAll().size() > 0) {
                return playerService.findPlayersByTeam(teamService.findAll().get(0).getId(), getContext().getOrder());
            } else {
                return new ArrayList<PlayerDTO>();
            }
        }

        return playerService.findPlayersByTeam(utTeam.getId(), getContext().getOrder());
    }

    public List<TeamDTO> getTeams() {
        if (teamService.findAll().size() > 0) {
            return teamService.findAll();
        } else {
            return new ArrayList<TeamDTO>();
        }
    }

    public Resolution create() {

        return new ForwardResolution("/player/create.jsp");
    }

    public boolean getCanCreate() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(PlayerServiceImpl.class
                .getDeclaredMethod("create", PlayerDTO.class));
    }
    
    public boolean getCanUpdate() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(PlayerServiceImpl.class
                .getDeclaredMethod("update", PlayerDTO.class));
    }
    
    public boolean getCanDelete() throws NoSuchMethodException, SecurityException
    {
      
        return sf.authorize(PlayerServiceImpl.class
                .getDeclaredMethod("delete", PlayerDTO.class));
    }
    
    @Override
    public void setContext(ActionBeanContext context) {
        this.context = (PlayerActionBeanContext) context;
    }

    @Override
    public PlayerActionBeanContext getContext() {
        return context;
    }
}
