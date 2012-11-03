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
public interface MatchDAO extends DAO<Match>
{    
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
     * @return List of Matches played by away team or empty List when team does not play any away match.
     */
    public List<Match> findByAwayTeam(Team team);
    
    
    public List<Match> findByRound(int round);
}
