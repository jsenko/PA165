package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author Peter Laurencik
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GoalDAOImpl implements GoalDAO
{
    
    @PersistenceContext
    EntityManager em;
   
    @Override
    public void create(Goal goal)
    {
        
        if(goal==null)
        {
            throw new IllegalArgumentException("Goal can not be null when new goal is created.");
        }
        
        em.persist(goal);        
 
    }
    
    @Override
    public void update(Goal goal)
    {
        if(goal==null)
        {
            throw new IllegalArgumentException("Can not update null goal");
        }
        
        em.merge(goal);
        
    }
    
    @Override
    public void delete(Goal goal)
    {
        if(goal==null)
        {
            throw new IllegalArgumentException("Can not delete null goal.");
        }
        if(em.find(Goal.class, goal.getId()) == null)
	{
            throw new IllegalArgumentException("Goal you are trying to delete does not exist.");
	}
        
        Goal merged = em.merge(goal);

        em.remove(merged);

    }
    
    @Override
    public Goal getById(Long id)
    {
        if(id==null)
        {
            throw new IllegalArgumentException("Can not find goal with null id.");
        }
        
        Goal goal = em.find(Goal.class, id);
        return goal;
 
    }
        
    @Override
    public Collection<Goal> findAll()
    {
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g").getResultList();
        
        return goals;
        
    }
    
    @Override
    public Collection<Goal> findByScorePlayer(Player player)
    {
        if(player == null)
        {
            throw new IllegalArgumentException("Can not find goal by null scored player.");
        }
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.scorePlayer='player'").getResultList();
        
        return goals;
        
    }

    @Override
    public Collection<Goal> findByAssistPlayer(Player player) {
        if(player == null)
        {
            throw new IllegalArgumentException("Can not find goal by null assist player.");
        }
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.assistPlayer='player'").getResultList();
        
        return goals;
        
    }

    @Override
    public Collection<Goal> findByMatch(Match match) { /*
        if(match == null)
        {
            throw new IllegalArgumentException("Can not find goal by null match.");
        }
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.assistPlayer='match'").getResultList();
        
        return goals;*/
    	
    	throw new UnsupportedOperationException("Use match.getGoals()");
        
    }
    
    
}
