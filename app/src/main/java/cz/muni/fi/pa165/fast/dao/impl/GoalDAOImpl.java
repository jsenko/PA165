package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class GoalDAOImpl implements GoalDAO
{
    private EntityManagerFactory emf;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public void create(Goal goal)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction emt = em.getTransaction();
        
        em.persist(goal);        
        
        emt.begin();
        emt.commit();
    }
    
    @Override
    public void update(Goal goal)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction emt = em.getTransaction();
        
        em.persist(goal);
        
        emt.begin();
        emt.commit();
    }
    
    @Override
    public void delete(Goal goal)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction emt = em.getTransaction();
        
        em.persist(goal);
        
        emt.begin();
            em.remove(goal);
        emt.commit();
    }
    
    @Override
    public Goal getById(Long id)
    {
        EntityManager em = emf.createEntityManager();
        
        Goal goal = em.find(Goal.class, id);
        
        return goal;
    }
        
    @Override
    public Collection<Goal> findAll()
    {
        EntityManager em = emf.createEntityManager();
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g").getResultList();
        return goals;
    }
    
    @Override
    public Collection<Goal> findByScorePlayer(Player player)
    {
        EntityManager em = emf.createEntityManager();
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.scorePlayer='player'").getResultList();
        return goals;
    }

    @Override
    public Collection<Goal> findByAssistPlayer(Player player) {
        EntityManager em = emf.createEntityManager();
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.assistPlayer='player'").getResultList();
        return goals;
    }

    @Override
    public Collection<Goal> findByMatch(Match match) {
        EntityManager em = emf.createEntityManager();
        
        Collection<Goal> goals = em.createQuery("SELECT g FROM Goal g WHERE g.assistPlayer='match'").getResultList();
        return goals;
    }
    
    
}
