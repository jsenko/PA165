package cz.muni.fi.pa165.fast.dao;

import java.util.Collection;

import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;

// TODO: persistence exceptions in javadoc?

/**
 * This interface describes data access object for Goal entity.
 * 
 * @author Jakub Senko
 * @version 1.0
 */
public interface GoalDAO extends DAO<Goal>
{
    
    /**
     * Find all goals scored by the specified Player.
     * 
     * @param player Player who scored the goals, cannot be null.
     * @return Collection of Goals or empty collection if there are no Goals scored by the Player.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Collection<Goal> findByScorePlayer(Player player);
    
    /**
     * Find all goals that the Player assisted with.
     * 
     * @param player Assist player, cannot be null.
     * @return Collection of Goals or empty collection if there are no Goals that the Player assisted with.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Collection<Goal> findByAssistPlayer(Player player);
    
    /**
     * Find all goals scored during the match.
     * @param match The match, cannot be null.
     * @return Collection of Goals or empty collection if there were no Goals during the Match.
     * @throws IllegalArgumentException if the argument is null.
     */
    public Collection<Goal> findByMatch(Match match);
    
    
}
