package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Implementation of the TeamDAO interface.
 *
 * @author Michal Kimle
 */
public class TeamDAOImpl implements TeamDAO {

    private EntityManagerFactory emf;

    @Override
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team is null");
        }

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(team);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team is null");
        }

        EntityManager em = emf.createEntityManager();

        try {
            if (em.find(Team.class, team.getId()) == null) {
                throw new IllegalArgumentException("team not in DB");
            }

            em.getTransaction().begin();
            em.merge(team);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team is null");
        }

        EntityManager em = emf.createEntityManager();

        try {
            Team manTeam = em.find(Team.class, team.getId());

            if (manTeam == null) {
                throw new IllegalArgumentException("team not in DB");
            }

            em.getTransaction().begin();
            em.remove(manTeam);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Team getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        EntityManager em = emf.createEntityManager();
        Team manTeam = null;
        
        try {
            manTeam = em.find(Team.class, id);

            if (manTeam == null) {
                throw new IllegalArgumentException("team not in DB");
            }
        } finally {
            em.close();
        }

        return manTeam;
    }

    @Override
    public Collection<Team> findAll() {
        EntityManager em = emf.createEntityManager();
        Collection<Team> teams = null;
        
        try {
            teams = em.createNamedQuery("Team.findAll").getResultList();
        } finally {
            em.close();
        }

        return teams;
    }

    @Override
    public Team findTeamByPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player is null");
        }

        EntityManager em = emf.createEntityManager();
        Team team = null;

        try {
            team = (Team) em.createNamedQuery("Team.findTeamByPlayer").setParameter("player", player).getSingleResult();
        } finally {
            em.close();
        }

        return team;
    }

    @Override
    public Team findHomeTeamByMatch(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("match is null");
        }

        EntityManager em = emf.createEntityManager();
        Team team = null;
        
        try {
            team = (Team) em.createNamedQuery("Team.findHomeTeamByMatch").setParameter("match", match).getSingleResult();
        } finally {
            em.close();
        }

        return team;
    }

    @Override
    public Team findAwayTeamByMatch(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("match is null");
        }

        EntityManager em = emf.createEntityManager();
        Team team = null;

        try {
            team = (Team) em.createNamedQuery("Team.findAwayTeamByMatch").setParameter("match", match).getSingleResult();
        } finally {
            em.close();
        }

        return team;
    }
}
