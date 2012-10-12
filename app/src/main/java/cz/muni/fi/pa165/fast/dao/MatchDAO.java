package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.List;

/**
 * Interface describes data actions for Match entity.
 * 
 * @author Peter Laurencik
 * @version 1.0
 */
public interface MatchDAO
{
    /**
     * Method inserts new Match into database. 
     * 
     * @param match instance of Match added to database. Can not be null.
     * @throws IllegalArgumentException if argument is null.
     */
    public void create(Match match);
    
    /**
     * Method updates old Match into database. 
     * 
     * @param match instance of Match updated in database. Can not be null.
     * @throws IllegalArgumentException if argument is null.
     */
    public void update(Match match);
    
    /**
     * Method deletes Match from database. 
     * 
     * @param match instance of Match deleted from database. Can not be null.
     * @throws IllegalArgumentException if argument is null.
     */
    public void delete(Match match);
    
    /**
     * Method finds Match in database by his id and returns an instance of match. 
     * 
     * @param id Match id. Can not be null.
     * @return New instance of Match or null, when such a Match does not exist.
     * @throws IllegalArgumentException if argument is null.
     */
    public Match getById(Long id);
    
    /**
     * Method finds all Matches in database. 
     * 
     * @return List of all Matches in database or empty List when no match is in database.
     */
    public List<Match> findAll();
    
    /**
     * Method finds all matches played by home team.
     * 
     * @param team Home team.
     * @return List of Matches played by home team or empty List when team does not play any home match.
     */
    public List<Match> findByHomeTeam(Team team);
    
    /**
     * Method finds all matches played by team away.
     * 
     * @param team Away team.
     * @return List of Matches played by home team or empty List when team does not play any away match.
     */
    public List<Match> findByAwayTeam(Team team);
}
