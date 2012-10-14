package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


/**
 * Implementation of PlayerDAO interface
 * 
 * @author Stefan Uhercik
 */
public class PlayerDAOImpl implements PlayerDAO
{

    private EntityManagerFactory emf;
    
    @Override
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public void create(Player player) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Player player) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(player);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Player player) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Player managed = em.merge(player);
        em.remove(managed);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Player getById(Long id) {
        EntityManager em = emf.createEntityManager();
        Player player = em.find(Player.class, id);
        em.close();
        return player;
    }

    @Override
    public Collection<Player> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELET p FROM Player p");
        Collection<Player> allPlayers = query.getResultList();
        em.close();
        return allPlayers;
    }
    
    
    @Override
    public Player getPlayerByScoredGoal(Goal goal) {
        /*
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELET p FROM Player p JOIN :goal g WHERE g.scorePlayer=p").setParameter("goal",goal);
        Player p = (Player) query.getSingleResult();
        return p;
        */
        throw new UnsupportedOperationException();
    }

    @Override
    public Player getPlayerByAssistedGoal(Goal goal) {
        /*
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELET p FROM Player p JOIN :goal g WHERE g.assistPlayer=p").setParameter("goal",goal);
        Player p = (Player) query.getSingleResult();
        return p;
        */
        throw new UnsupportedOperationException();
        
    }

    @Override
    public List<Player> findPlayersByTeam(Team team) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELET p FROM Player p JOIN :team t WHERE p IN t.players").setParameter("team",team);
        List<Player> list = query.getResultList();
        return list;
    }

    

    

}
