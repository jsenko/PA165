package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import javax.ejb.Local;

/**
 * This interface represents data access object for Team
 * 
 * @author Stefan Uhercik
 */
@Local
public interface TeamDAO extends DAO<Team>
{

    /**
     * Finds the team that Player plays for
     * 
     * @param player Player whose Team is to be found
     * @return Team that Player plays for
     */
    public Team findTeamByPlayer(Player player);
    
    /**
     * Finds the team that played match on home pitch
     * 
     * @param match Match played
     * @return Team that played match on home pitch
     */
    public Team findHomeTeamByMatch(Match match);
    
    /**
     * Finds the away Team of the Match
     * 
     * @param match Match played
     * @return away Team of the Match
     */
    public Team findAwayTeamByMatch(Match match);  
}
