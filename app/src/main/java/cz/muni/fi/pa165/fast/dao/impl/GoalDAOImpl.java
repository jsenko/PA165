package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * 
 * @author Peter Laurencik
 */
public class GoalDAOImpl implements GoalDAO
{
    private EntityManagerFactory emf;

    @Override
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public void create(Goal goal)
    {
        
        if(goal==null)
        {
            throw new IllegalArgumentException("Goal can not be null when new goal is created.");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            EntityTransaction emt = em.getTransaction();

            em.persist(goal);        

            emt.begin();
            emt.commit();
        }finally{
          em.close();  
        }
    }
    
    @Override
    public void update(Goal goal)
    {
        if(goal==null)
        {
            throw new IllegalArgumentException("Can not update null goal");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();        
            em.merge(goal);
            em.getTransaction().commit();
        }finally{
            em.close();
        }

    }
    
    @Override
    public void delete(Goal goal)
    {
        if(goal==null)
        {
            throw new IllegalArgumentException("Can not delete null goal.");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            EntityTransaction emt = em.getTransaction();

            goal = em.merge(goal);

            emt.begin();
                em.remove(goal);
            emt.commit();
        }finally{
            em.close();
        }
    }
    
    @Override
    public Goal getById(Long id)
    {
        if(id==null)
        {
            throw new IllegalArgumentException("Can not find goal with null id.");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            Goal goal = em.find(Goal.class, id);
            return goal;
        }finally{
            em.close();
        }
        
    }
        
    @Override
    public Collection<Goal> findAll()
    {
        EntityManager em = emf.createEntityManager();
        try{
            Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g").getResultList();
            return goals;
        }finally{
            em.close();
        }
        
    }
    
    @Override
    public Collection<Goal> findByScorePlayer(Player player)
    {
        if(player == null)
        {
            throw new IllegalArgumentException("Can not find goal by null scored player.");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.scorePlayer='player'").getResultList();
            return goals;
        }finally{
            em.close();
        }
    }

    @Override
    public Collection<Goal> findByAssistPlayer(Player player) {
        if(player == null)
        {
            throw new IllegalArgumentException("Can not find goal by null assist player.");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.assistPlayer='player'").getResultList();
            return goals;
        }finally{
            em.close();
        }
    }

    @Override
    public Collection<Goal> findByMatch(Match match) {
        if(match == null)
        {
            throw new IllegalArgumentException("Can not find goal by null match.");
        }
        
        EntityManager em = emf.createEntityManager();
        
        try{
            Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.assistPlayer='match'").getResultList();
            return goals;
        }finally{
            em.close();
        }
    }
    
    
}
