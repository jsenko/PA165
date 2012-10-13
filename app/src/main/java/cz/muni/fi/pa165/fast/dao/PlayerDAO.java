package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.List;

/**
 *Interface of DAO object of entity Player
 * 
 * @author Michal Kimle
 */
public interface PlayerDAO
{
    /**
     * Creates new Player in the database.
     * 
     * @param player
     * @throws IllegalArgumentException if argument is null
     */
    public void createPlayer(Player player);
    
    /**
     * Updates a Player in the database.
     * 
     * @param player
     * @throws IllegalArgumentException if argument is null
     */
    public void updatePlayer(Player player);
    
    /**
     * Removes a Player in the database.
     * 
     * @param player
     * @throws IllegalArgumentException if argument is null
     */
    public void removePlayer(Player player);
    
    /**
     * Retrieves a Player with given id from database.
     * 
     * @param id Id of Player. Cannot be null.
     * @return Player with the specified id or null if it does not exist.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Player getPlayerById(Long id);
    
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
     * Retrieves all Players
     * 
     * @return List of all Players in database or empty List if database is empty
     */
    public List<Player> findAllPlayers();
    
    /**
     * Retrieves Players from given team
     * 
     * @return List of Players from team or empty List if player doesn't belong to any team
     */
    public List<Player> findPlayersByTeam(Team team);
}
