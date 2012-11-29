package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Jakub Senko
 */
public class TeamDAOTest {

    private static Context context;
    private static TeamDAO dao;
    private static TeamFakeEntityManager fem;

    @BeforeClass
    public static void setUpClass() throws Exception {
        context = EJBContainer.createEJBContainer().getContext();
        dao = (TeamDAO) context.lookup("java:global/app/TeamDAOImpl");
        fem = (TeamFakeEntityManager) context.lookup("java:global/app/TeamFakeEntityManager");
    }

    @AfterClass
    public static void setDownClass() throws NamingException {
        context.close();
    }

    @Test
    public void create() {
        Team t = new Team();
        t.setName("FC Losers");

        // sanity check
        dao.create(t);

        Team tt = fem.find(Team.class, t.getId());
        assertNotNull(tt);
        assertEquals("FC Losers", tt.getName());

        // null argument check
        try {
            dao.create(null);
            fail();
        } catch (EJBException e) {
            // ok
        }
        
        //cleanup
        fem.remove(t);
    }

    @Test
    public void update() {
        Team t = new Team();
        t.setName("FC Losers");

        try {
            dao.update(null);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        try {
            dao.update(t);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        fem.persist(t);

        Team tt = new Team();
        tt.setId(t.getId());
        tt.setName("FC Winners");

        dao.update(tt);

        assertEquals(t.getId(), tt.getId());
        assertEquals("FC Winners", tt.getName());
        
        //cleanup
        fem.remove(t);
    }

    @Test
    public void delete() {
        Team t = new Team();
        t.setName("FC Losers");

        try {
            dao.delete(null);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        try {
            dao.delete(t);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        fem.persist(t);

        dao.delete(t);

        Team tt = (Team) fem.find(Team.class, t.getId());
        assertNull(tt);
    }

    @Test
    public void findAll() {
        assertEquals(0, dao.findAll().size());

        Team t = new Team();
        t.setName("First");
        Team tt = new Team();
        tt.setName("Second");

        fem.persist(t);
        fem.persist(tt);

        Collection<Team> result = dao.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(t));
        assertTrue(result.contains(tt));
        
        //cleanup
        fem.remove(t);
        fem.remove(tt);
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

        fem.persist(t);
        fem.persist(tt);

        try {
            dao.findTeamByPlayer(null);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        Team res = dao.findTeamByPlayer(p);
        Team res2 = dao.findTeamByPlayer(pp2);

        assertEquals("First", res.getName());
        assertEquals("Second", res2.getName());
        
        //cleanup
        fem.remove(t);
        fem.remove(tt);
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

        fem.persist(t);
        fem.persist(tt);
        fem.persist(m);

        try {
            dao.findHomeTeamByMatch(null);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        Team res = dao.findHomeTeamByMatch(m);

        assertEquals("Home", res.getName());
        
        //cleanup
        fem.remove(t);
        fem.remove(tt);
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

        fem.persist(t);
        fem.persist(tt);
        fem.persist(m);

        try {
            dao.findAwayTeamByMatch(null);
            fail();
        } catch (EJBException e) {
            //ok 
        }

        Team res = dao.findAwayTeamByMatch(m);

        assertEquals("Away", res.getName());
        
        //cleanup
        fem.remove(t);
        fem.remove(tt);
    }

    @Stateless
    public static class TeamFakeEntityManager {

        @PersistenceContext(name = "TestPU")
        private EntityManager em;

        public void remove(Team t) {
            em.remove(em.find(Team.class, t.getId()));
        }

        public void persist(Object o) {
            em.persist(o);
        }

        public Team find(Class<Team> c, Object o) {
            return em.find(c, o);
        }
    }
}
