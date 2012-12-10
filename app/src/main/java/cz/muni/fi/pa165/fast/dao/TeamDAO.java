package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;

/**
 * This interface represents data access object for Team
 *
 * @author Stefan Uhercik
 */
public interface TeamDAO extends DAO<Team> {

    /**
     * Finds the team that Player plays for
     *
     * @param player Player whose Team is to be found
     * @return Team that Player plays for
     */
    public Team findTeamByPlayer(Player player);
}
