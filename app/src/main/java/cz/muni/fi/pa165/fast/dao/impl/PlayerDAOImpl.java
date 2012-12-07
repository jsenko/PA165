package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementation of PlayerDAO interface
 *
 * @author Stefan Uhercik
 */
@Local(value = PlayerDAO.class)
@Stateless(mappedName = "PlayerDAOImpl")
public class PlayerDAOImpl implements PlayerDAO {

    @PersistenceContext(name = "TestPU")
    private EntityManager em;

    @Override
    public void create(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player is null");
        }
        em.persist(player);
    }

    @Override
    public void update(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player is null");
        }

        if (em.find(Player.class, player.getId()) == null) {
            throw new IllegalArgumentException("player not in DB");
        }
        em.merge(player);
    }

    @Override
    public void delete(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player is null");
        }

        Player managed = em.find(Player.class, player.getId());
        if (managed == null) {
            throw new IllegalArgumentException("player not in DB");
        }

        List<Goal> goals = em.createQuery("select g from Goal g where g.scorePlayer = :player")
                .setParameter("player", managed)
                .getResultList();
        
        goals.addAll(em.createQuery("select g from Goal g where g.assistPlayer = :player").setParameter("player", managed).getResultList());

        for (Goal g : goals) {
            em.remove(g);
        }

        em.remove(managed);
    }

    @Override
    public Player getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        Player managed = em.find(Player.class, id);
        if (managed == null) {
            throw new IllegalArgumentException("player not in DB");
        }
        return managed;
    }

    @Override
    public Collection<Player> findAll() {
        Query query = em.createQuery("SELECT p FROM Player p");
        Collection<Player> allPlayers = query.getResultList();
        return allPlayers;
    }

    @Override
    public List<Player> findPlayersByTeam(Team team) {
        Query query = em.createQuery("SELECT p FROM Player p WHERE p.team=:team").setParameter("team", team);
        List<Player> list = query.getResultList();
        return list;
    }
}