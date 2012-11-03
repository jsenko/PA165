package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.dao.impl.TeamDAOImpl;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jakub Senko
 */
public class TeamDAOTest {

    @PersistenceContext
    private EntityManager em;
    private TeamDAO dao;

    @Before
    public void seUp() {
        dao = new TeamDAOImpl();
    }

    @Test
    public void create() {
        Team t = new Team();
        t.setName("FC Losers");

        // sanity check
        dao.create(t);

        Team tt = em.find(Team.class, t.getId());
        assertNotNull(tt);
        assertEquals("FC Losers", tt.getName());

        // null argument check
        try {
            dao.create(null);
            fail();
        } catch (IllegalArgumentException e) {
            // ok
        }
    }

    @Test
    public void update() {
        Team t = new Team();
        t.setName("FC Losers");

        try {
            dao.update(null);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        try {
            dao.update(t);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        em.persist(t);
        em.clear();

        Team tt = new Team();
        tt.setId(t.getId());
        tt.setName("FC Winners");

        dao.update(tt);

        Team ttt = (Team) em.find(Team.class, t.getId());
        assertEquals(t.getId(), ttt.getId());
        assertEquals("FC Winners", ttt.getName());
    }

    @Test
    public void delete() {
        Team t = new Team();
        t.setName("FC Losers");

        try {
            dao.delete(null);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        try {
            dao.delete(t);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        em.persist(t);
        em.clear();

        dao.delete(t);

        Team tt = (Team) em.find(Team.class, t.getId());
        assertNull(tt);
    }

    @Test
    public void findAll() {
        assertEquals(0, dao.findAll().size());

        Team t = new Team();
        t.setName("First");
        Team tt = new Team();
        tt.setName("Second");

        em.persist(t);
        em.persist(tt);
        em.clear();

        Collection<Team> result = dao.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(t));
        assertTrue(result.contains(tt));
    }

    @Test
    public void findTeamByPlayer() {
        Player p = new Player();
        p.setName("Pat");

        Player pp = new Player();
        pp.setName("Mat");

        Player pp2 = new Player();
        pp2.setName("Mat2");

        Team t = new Team();
        t.setName("First");
        t.setPlayers(new ArrayList<Player>());
        t.getPlayers().add(p);

        Team tt = new Team();
        tt.setName("Second");
        tt.setPlayers(new ArrayList<Player>());
        tt.getPlayers().add(pp);
        tt.getPlayers().add(pp2);
        p.setTeam(t);
        pp.setTeam(tt);
        pp2.setTeam(tt);

        em.persist(p);
        em.persist(pp);
        em.persist(pp2);
        em.persist(t);
        em.persist(tt);
        //em.clear();

        try {
            dao.findTeamByPlayer(null);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        Team res = dao.findTeamByPlayer(p);
        Team res2 = dao.findTeamByPlayer(pp2);

        assertEquals("First", res.getName());
        assertEquals("Second", res2.getName());
    }

    @Test
    public void findHomeTeamByMatch() {
        Team t = new Team();
        t.setName("Home");

        Team tt = new Team();
        tt.setName("Away");

        Match m = new Match();
        m.setHomeTeam(t);
        m.setAwayTeam(tt);

        em.persist(t);
        em.persist(tt);
        em.persist(m);

        try {
            dao.findHomeTeamByMatch(null);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        Team res = dao.findHomeTeamByMatch(m);

        assertEquals("Home", res.getName());
    }

    @Test
    public void findAwayTeamByMatch() {
        Team t = new Team();
        t.setName("Home");

        Team tt = new Team();
        tt.setName("Away");

        Match m = new Match();
        m.setHomeTeam(t);
        m.setAwayTeam(tt);

        em.getTransaction().begin();
        em.persist(t);
        em.persist(tt);
        em.persist(m);
        em.getTransaction().commit();

        try {
            dao.findAwayTeamByMatch(null);
            fail();
        } catch (IllegalArgumentException e) { /* ok */ }

        Team res = dao.findAwayTeamByMatch(m);

        assertEquals("Away", res.getName());
    }
}
