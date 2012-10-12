package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;

/**
 * This interface represents data access object for Team
 * 
 * @author Stefan
 */
public interface TeamDAO
{
    /**
     * Creates new team in the database
     * 
     * @param team Team to be created, cannot be null.
     */
    public void create(Team team);
    
    /**
     * Updates team in the database
     * 
     * @param team Team to be updated, cannot be null.
     */
    public void update(Team team);
    
    /**
     * Deletes team from database
     * 
     * @param team Team to be deleted, cannot be null
     */
    public void delete(Team team);
    
    /**
     * Finds the team by id
     * 
     * @param id Id of the team to be found, cannot be null
     * @return Team with specified id.
     */
    public Team getById(Long id);
    
    /**
     * Finds all the teams from database
     * 
     * @return Collection of all teams from database or empty Collection if there are no teams registered
     */
    public Collection<Team> findAll();
    
    /**
     * Finds the team that Player plays for
     * 
     * @param player
     * @return Team that Player plays for
     */
    public Team findByPlayer(Player player);
}
