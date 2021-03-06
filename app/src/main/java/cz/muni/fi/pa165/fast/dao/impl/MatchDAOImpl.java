package cz.muni.fi.pa165.fast.dao.impl;

import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the MatchDAO interface.
 *
 * @author Jakub Senko
 * @version 1.0
 */
@Local(value = MatchDAO.class)
@Stateless
public class MatchDAOImpl implements MatchDAO {

    @PersistenceContext(name = "TestPU")
    private EntityManager em;

    @Override
    public void create(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("Match is null.");
        }
        em.persist(match);
    }

    @Override
    public void update(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("Match is null.");
        }

        if (em.find(Match.class, match.getId()) == null) {
            throw new IllegalArgumentException("Match does not exist.");
        }

        em.merge(match);
    }

    @Override
    public void delete(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("Match is null.");
        }

        if (em.find(Match.class, match.getId()) == null) {
            throw new IllegalArgumentException("Match does not exist.");
        }

        Match managed = em.merge(match);

        List<Goal> goals = em.createQuery("select g from Goal g where g.match = :match")
                .setParameter("match", managed)
                .getResultList();

        for (Goal g : goals) {
            em.remove(g);
        }

        em.remove(managed);
    }

    @Override
    public Match getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }

        Match manMatch = em.find(Match.class, id);
        if (manMatch == null) {
            throw new IllegalArgumentException("match not in DB");
        }

        return manMatch;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Match> findAll() {
        return em.createQuery("select m from Match m").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Match> findByHomeTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team is null.");
        }

        return em.createQuery("select m from Match m where m.homeTeam = :team")
                .setParameter("team", team)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Match> findByAwayTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team is null.");
        }

        return em.createQuery("select m from Match m where m.awayTeam = :team")
                .setParameter("team", team)
                .getResultList();
    }
}
