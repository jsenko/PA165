package cz.muni.fi.pa165.fast.listener;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TeamServletContextListener implements ServletContextListener {
@Override
  public void contextInitialized(ServletContextEvent event) {
    event.getServletContext().setAttribute("selectedTeam", new TeamDTO());
}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
