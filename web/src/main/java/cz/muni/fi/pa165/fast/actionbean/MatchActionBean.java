package cz.muni.fi.pa165.fast.actionbean;

import com.samaxes.stripejb3.EJBBean;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.service.MatchService;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

//@UrlBinding("/matches/all")
//@EJB(name = "matchServiceImpl", beanInterface = "cz.muni.fi.pa165.fast.service.MatchService")
public class MatchActionBean implements ActionBean{
    
    private ActionBeanContext context;
    
    @EJBBean("java:global/myapp/MatchServiceImpl!cz.muni.fi.pa165.fast.service.MatchService")
    protected MatchService matchService;
    
    @DefaultHandler
    public Resolution all() {
        return new ForwardResolution("/index.jsp");
    }
    
    public List<MatchDTO> getMatches() {
        return matchService.findAll();
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
