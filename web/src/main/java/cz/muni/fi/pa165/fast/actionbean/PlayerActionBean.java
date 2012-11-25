package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import cz.muni.fi.pa165.fast.service.TeamService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@UrlBinding("/players/{$event}")
public class PlayerActionBean implements ActionBean {

    private ActionBeanContext context;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private PlayerDTO player;
    private TeamDTO team;
    @EJBBean("java:global/myapp/PlayerServiceImpl!cz.muni.fi.pa165.fast.service.PlayerService")
    protected PlayerService playerService;
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    
    private TeamDTO utTeam;

    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/players.jsp");
    }

    public Resolution selectTeam() {
        if (team == null || team.getId() == 0) {
            System.out.println("No team or no team id");
        } else {
            utTeam = teamService.getById(team.getId());
            System.out.println("Some team");
        }
        return new RedirectResolution(this.getClass(), "all");
    }

    public Resolution add() {
        System.out.println(player);
        playerService.create(player);
        return new RedirectResolution(this.getClass(), "all");
    }
    /*
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
     }*/

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

    public List<TeamDTO> getTeams() {
        if (utTeam == null || utTeam.getId() == 0) {
            System.out.println("No team or no team id --> returning all teams");
            return teamService.findAll();
        }
        int i;
        List<TeamDTO> list = teamService.findAll();
        for (i = 0; i < list.size(); i++) {
            if (utTeam.getId() == list.get(i).getId()) {
                break;
            }
        }

        TeamDTO tmp = list.get(i);
        list.set(i, list.get(0));
        list.set(0, tmp);

        return list;
    }

    public List<PlayerDTO> getPlayers() {
        if (utTeam == null || utTeam.getId() == 0) {
            System.out.println("No team or no team id --> returning players of first team");
            if (teamService.findAll().size() > 0) {
                return playerService.findPlayersByTeam(teamService.findAll().get(0).getId(), PlayerOrderBy.NAME);
            } else {
                return new ArrayList<PlayerDTO>();
            }
        }

        return playerService.findPlayersByTeam(utTeam.getId(), PlayerOrderBy.NAME);
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
