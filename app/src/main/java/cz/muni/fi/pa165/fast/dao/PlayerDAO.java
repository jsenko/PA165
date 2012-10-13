package cz.muni.fi.pa165.fast.dao;

import java.util.List;

import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;

/**
 *Interface of DAO object of entity Player
 * 
 * @author Michal Kimle
 */
public interface PlayerDAO extends DAO<Player>
{    
    /**
     * Retrieves a Player who scored given goal.
     * 
     * @param goal Scored goal. Cannot be null.
     * @return Player who scored the goal or null if it does not exist.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Player getPlayerByScoredGoal(Goal goal);
    
    /**
     * Retrieves a Player who assisted given goal.
     * 
     * @param goal Assisted goal. Cannot be null.
     * @return Player who assisted the goal or null if it does not exist.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Player getPlayerByAssistedGoal(Goal goal);
    
    /**
     * Retrieves Players from given team
     * 
     * @return List of Players from team or empty List if player doesn't belong to any team
     */
    public List<Player> findPlayersByTeam(Team team);
}
