package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * Implementation of PlayerDAO interface
 * 
 * @author Stefan Uhercik
 */
@Stateless
public class PlayerDAOImpl implements PlayerDAO
{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Player player) {
        em.persist(player);
    }

    @Override
    public void update(Player player) {
        em.merge(player);
    }

    @Override
    public void delete(Player player) {
        Player managed = em.merge(player);
        em.remove(managed);
    }

    @Override
    public Player getById(Long id) {
        Player player = em.find(Player.class, id);
        return player;
    }

    @Override
    public Collection<Player> findAll() {
        Query query = em.createQuery("SELECT p FROM Player p");
        Collection<Player> allPlayers = query.getResultList();
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
        Query query = em.createQuery("SELECT p FROM Player p JOIN :team t WHERE p IN t.players").setParameter("team",team);
        List<Player> list = query.getResultList();
        return list;
    } 
}
