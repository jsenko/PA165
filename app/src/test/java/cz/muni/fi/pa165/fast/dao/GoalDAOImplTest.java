package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan Uhercik
 */
public class GoalDAOImplTest {

    private static Context context;
    private static GoalDAO gdaoi;
    private static GoalFakeEntityManager em;

    @BeforeClass
    public static void setUpClass() throws Exception {

        context = EJBContainer.createEJBContainer().getContext();
        gdaoi = (GoalDAO) context.lookup("java:global/app/GoalDAOImpl");
        em = (GoalFakeEntityManager) context.lookup("java:global/app/GoalFakeEntityManager");
    }

    @AfterClass
    public static void tearDownClass() throws NamingException {
        context.close();
    }

    /**
     * Test of create method, of class GoalDAOImpl.
     */
    @Test
    public void testCreate() {
        Goal goal = new Goal();

        gdaoi.create(goal);

        Goal founded = em.find(Goal.class, goal.getId());
        assertNotNull(goal.getId());
        assertEquals(goal.getId(), founded.getId());

        em.remove(goal);
    }

    @Test
    public void testCreateInvalid() {
        Goal goal = null;
        try {
            gdaoi.create(goal);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    /**
     * Test of update method, of class GoalDAOImpl.
     */
    @Test
    public void testUpdate() {
        Goal goal = new Goal();

        em.persist(goal);

        int minute = 12;
        goal.setGoalMinute(minute);

        gdaoi.update(goal);

        Goal goalFromDB = em.find(Goal.class, goal.getId());

        assertEquals(minute, goalFromDB.getGoalMinute());

        em.remove(goal);
    }

    @Test
    public void testInvalidUpdate() {
        Goal goal = null;
        try {
            gdaoi.update(goal);
            fail();
        } catch (EJBException ex) {
            //OK
        }

        goal = new Goal();
        goal.setId(Long.MAX_VALUE);
        try {
            gdaoi.update(goal);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    /**
     * Test of delete method, of class GoalDAOImpl.
     */
    @Test
    public void testDelete() {
        Goal goal = new Goal();

        em.persist(goal);

        Long goalId = goal.getId();

        gdaoi.delete(goal);

        Goal goalFromDB = em.find(Goal.class, goalId);
        assertNull(goalFromDB);
    }

    @Test
    public void testInvalidDelete() {
        Goal goal = null;
        try {
            gdaoi.delete(goal);
            fail();
        } catch (EJBException ex) {
            //OK
        }

        goal = new Goal();
        goal.setId(Long.MAX_VALUE);
        try {
            gdaoi.delete(goal);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    @Test
    public void testGetById() {
        Goal goal = new Goal();
        int minute = 25;
        goal.setGoalMinute(minute);

        em.persist(goal);

        Long goalId = goal.getId();

        Goal dbGoal = gdaoi.getById(goalId);

        assertEquals(minute, dbGoal.getGoalMinute());

        em.remove(goal);
    }

    @Test
    public void testInvalidGetById() {
        try {
            gdaoi.getById(null);
            fail();
        } catch (EJBException ex) {
            //OK
        }

        try {
            gdaoi.getById(Long.MAX_VALUE);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    @Test
    public void testFindAll() {
        Random r = new Random();

        List<Goal> list = new LinkedList<Goal>();

        Goal g = new Goal();

        g.setGoalMinute(r.nextInt(100));
        list.add(g);
        em.persist(g);

        Goal g2 = new Goal();
        g2.setGoalMinute(r.nextInt(100));
        list.add(g2);
        em.persist(g2);

        List<Goal> goalsFromDB = (List<Goal>) gdaoi.findAll();

        assertEquals(list, goalsFromDB);

        em.remove(g);
        em.remove(g2);
    }

    @Test
    public void testFindByScorePlayer() {
        Player player = new Player();

        Goal g1 = new Goal();
        g1.setGoalMinute(10);
        g1.setScorePlayer(player);
        Goal g2 = new Goal();
        g2.setGoalMinute(20);
        g2.setScorePlayer(player);
        Goal g3 = new Goal();
        g3.setGoalMinute(30);

        em.persist(player);
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);

        Collection<Goal> goals = gdaoi.findByScorePlayer(player);

        assertEquals(2, goals.size());

        for (Goal goal : goals) {
            if (goal.getId().equals(g1.getId())) {
                assertEquals(goal.getGoalMinute(), g1.getGoalMinute());
            }
            if (goal.getId().equals(g2.getId())) {
                assertEquals(goal.getGoalMinute(), g2.getGoalMinute());
            }
        }

        em.remove(g1);
        em.remove(g2);
        em.remove(g3);
    }

    @Test
    public void testFindByAssistPlayer() {
        Player player = new Player();

        Goal g1 = new Goal();
        g1.setGoalMinute(10);
        g1.setAssistPlayer(player);
        Goal g2 = new Goal();
        g2.setGoalMinute(20);
        g2.setAssistPlayer(player);
        Goal g3 = new Goal();
        g3.setGoalMinute(30);

        em.persist(player);
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);

        Collection<Goal> goals = gdaoi.findByAssistPlayer(player);

        assertEquals(2, goals.size());

        for (Goal goal : goals) {
            if (goal.getId().equals(g1.getId())) {
                assertEquals(goal.getGoalMinute(), g1.getGoalMinute());
            }
            if (goal.getId().equals(g2.getId())) {
                assertEquals(goal.getGoalMinute(), g2.getGoalMinute());
            }
        }

        em.remove(g1);
        em.remove(g2);
        em.remove(g3);
    }

    @Test
    public void testFindByMatch() {
        Match match = new Match();

        Goal g1 = new Goal();
        g1.setGoalMinute(10);
        g1.setMatch(match);
        Goal g2 = new Goal();
        g2.setGoalMinute(20);
        g2.setMatch(match);
        Goal g3 = new Goal();
        g3.setGoalMinute(30);

        em.persist(match);
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);

        Collection<Goal> goals = gdaoi.findByMatch(match);

        assertEquals(2, goals.size());

        for (Goal goal : goals) {
            if (goal.getId().equals(g1.getId())) {
                assertEquals(goal.getGoalMinute(), g1.getGoalMinute());
            }
            if (goal.getId().equals(g2.getId())) {
                assertEquals(goal.getGoalMinute(), g2.getGoalMinute());
            }
        }

        em.remove(g1);
        em.remove(g2);
        em.remove(g3);
    }

    @Stateless
    public static class GoalFakeEntityManager {

        @PersistenceContext(name = "TestPU")
        private EntityManager em;

        public void remove(Goal g) {
            em.remove(em.find(Goal.class, g.getId()));
        }

        public void persist(Object o) {
            em.persist(o);
        }

        public Goal find(Class<Goal> c, Object o) {
            return em.find(c, o);
        }
    }
}
