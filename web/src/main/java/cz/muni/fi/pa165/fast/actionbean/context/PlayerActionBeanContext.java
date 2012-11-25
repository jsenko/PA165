package cz.muni.fi.pa165.fast.actionbean.context;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.service.PlayerOrderBy;
import net.sourceforge.stripes.action.ActionBeanContext;

public class PlayerActionBeanContext extends ActionBeanContext {

    private static final String SELECTED_TEAM = "selectedTeam";
    private static final String ORDER = "order";

    public void setSelectedTeam(TeamDTO dto) {
        set(SELECTED_TEAM, dto);
    }

    public TeamDTO getSelectedTeam() {
        TeamDTO dto = new TeamDTO();
        return get(SELECTED_TEAM, dto);
    }
    
    public void setOrder(PlayerOrderBy order){
        set(ORDER, order);
    }
    
    public PlayerOrderBy getOrder(){
        PlayerOrderBy order = PlayerOrderBy.NAME;
        return get(ORDER, order);
    }

    protected void set(String key, Object value) {
        getRequest().getSession().setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    protected <T> T get(String key, T defaultValue) {
        T value = (T) getRequest().getSession().getAttribute(key);
        if (value == null) {
            value = defaultValue;
            set(key, value);
        }
        return value;
    }
}
