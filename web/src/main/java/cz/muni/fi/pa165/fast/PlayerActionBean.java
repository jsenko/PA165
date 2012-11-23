package cz.muni.fi.pa165.fast;

import javax.ejb.EJB;

import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import cz.muni.fi.pa165.fast.service.PlayerService;
import cz.muni.fi.pa165.fast.service.impl.PlayerServiceImpl;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * 
 * @author Jakub Senko
 */
@UrlBinding("/player/{$event}/{player.id}")
public class PlayerActionBean implements ActionBean
{

	private ActionBeanContext context;
	
	private Player player;
	
	@EJB
	private PlayerServiceImpl playerService;
	
    @DefaultHandler
    public Resolution all()
    {
    	playerService.findAll(PlayerOrderBy.AGE);
        return new ForwardResolution("/player/table.jsp");
    }
	
	@Override
	public ActionBeanContext getContext()
	{
		return context;
	}

	@Override
	public void setContext(ActionBeanContext context)
	{
		this.context = context;
	}

}
