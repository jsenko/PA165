package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.MatchService;
import cz.muni.fi.pa165.fast.service.TeamService;

import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * 
 * @author Jakub Senko
 *
 */
@UrlBinding("/matches/{$event}/{matchDTO.id}")
public class MatchActionBean implements ActionBean{
    
    private ActionBeanContext context;
    
    @EJBBean("java:global/myapp/MatchServiceImpl!cz.muni.fi.pa165.fast.service.MatchService")
    protected MatchService matchService;
    
    @EJBBean("java:global/myapp/TeamServiceImpl!cz.muni.fi.pa165.fast.service.TeamService")
    protected TeamService teamService;
    
    private MatchDTO matchDTO; //todo process date
    
    private int date;
    private int month;
    private int year;
    
    @DefaultHandler
    public Resolution all() {

        return new ForwardResolution("/index.jsp");
    }
    
    public List<MatchDTO> getMatches() {
        return matchService.findAll();
    }
    
    public List<TeamDTO> getTeams()
    {
    	return teamService.findAll();
    }
    

    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadMatchFromDatabase() {
    	
        String ids = context.getRequest().getParameter("matchDTO.id");
        if (ids == null) {
            return;
        }
        
        
        matchDTO = matchService.getById(Long.parseLong(ids));
        date = matchDTO.getDate().getDate();
        month = matchDTO.getDate().getMonth();
        year = matchDTO.getDate().getYear();
        System.out.println(matchDTO);
    }

    
    public Resolution create()
    {
    	return new ForwardResolution("/match/create.jsp");
    }

    public Resolution add()
    {
    	
    	Date d = new Date();
    	d.setDate(date);
    	d.setMonth(month);
    	d.setYear(year);
    	
    	matchDTO.setDate(d);

    	matchService.create(matchDTO);
    	
        return new ForwardResolution(this.getClass(), "all");
    }



    
    public Resolution edit() {

        return new RedirectResolution("/match/edit.jsp?matchDTO.id="+matchDTO.getId());// hack, neviem preco to nefunguje normalne
    }
    
    public Resolution save() {
    	
        matchService.update(matchDTO);
        return new RedirectResolution(this.getClass(), "all");
    }


	public MatchDTO getMatchDTO() {
		return matchDTO;
	}

	public void setMatchDTO(MatchDTO matchDTO) {
		
		this.matchDTO = matchDTO;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
