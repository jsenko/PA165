package cz.muni.fi.pa165.fast.actionbean.context;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import net.sourceforge.stripes.action.ActionBeanContext;

public class TeamActionBeanContext extends ActionBeanContext {
    public TeamActionBeanContext(){
        super();
    }
    
    public TeamDTO getSelectedTeam() {
      return (TeamDTO) getServletContext().getAttribute("selectedTeam");
  }
}
