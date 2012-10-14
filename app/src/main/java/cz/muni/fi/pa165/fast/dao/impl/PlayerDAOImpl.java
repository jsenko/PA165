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
        Query query = em.createQuery("select p from Player p");
        Collection<Player> allPlayers = query.getResultList();
        em.close();
        return allPlayers;
    }
    
    
    @Override
    public Player getPlayerByScoredGoal(Goal goal) {
        return goal.getScorePlayer();
    }

    @Override
    public Player getPlayerByAssistedGoal(Goal goal) {
        return goal.getAssistPlayer();
    }

    @Override
    public List<Player> findPlayersByTeam(Team team) {
        return team.getPlayers();
    }

    

    

}
