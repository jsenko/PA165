package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.List;

/**
 * Interface of DAO object of entity Player
 *
 * @author Michal Kimle
 */
public interface PlayerDAO extends DAO<Player> {

    /**
     * Retrieves Players from given team
     *
     * @return List of Players from team or empty List if player doesn't belong
     * to any team
     */
    public List<Player> findPlayersByTeam(Team team);
}
