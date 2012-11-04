/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.dao.impl.GoalDAOImpl;
import cz.muni.fi.pa165.fast.model.Goal;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan Uhercik
 */
public class GoalDAOImplTest {

    private GoalDAOImpl gdaoi;
    private EntityManagerFactory emf;
    private EntityManager em;

    public GoalDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        
        gdaoi = new GoalDAOImpl();
        
        Field f = gdaoi.getClass().getDeclaredField("em");
        f.setAccessible(true);
        f.set(gdaoi, em);
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    /**
     * Test of create method, of class GoalDAOImpl.
     */
    @Test
    public void testCreate() {
        Goal goal = new Goal();
        em.getTransaction().begin();
        gdaoi.create(goal);
        em.getTransaction().commit();
        assertNotNull(goal.getId());
    }

    /**
     * Test of update method, of class GoalDAOImpl.
     */
    @Test
    public void testUpdate() {
        Goal goal = new Goal();

        em.getTransaction().begin();
        em.persist(goal);
        em.getTransaction().commit();

        long milis = 895465778;
        goal.setGoalTime(new Date(milis));

        gdaoi.update(goal);

        Goal goalFromDB = em.find(Goal.class, goal.getId());

        assertEquals(milis, goalFromDB.getGoalTime().getTime());

    }

    /**
     * Test of delete method, of class GoalDAOImpl.
     */
    @Test
    public void testDelete() {
        Goal goal = new Goal();
        em.getTransaction().begin();
        em.persist(goal);
        em.getTransaction().commit();

        Long goalId = goal.getId();

        gdaoi.delete(goal);

        Goal goalFromDB = em.find(Goal.class, goalId);
        assertNull(goalFromDB);
    }

    @Test
    public void testFindAll() {
        Random r = new Random();

        List<Goal> list = new LinkedList<Goal>();
        em.getTransaction().begin();

        Goal g = new Goal();

        g.setGoalTime(new Date(r.nextInt(15000)));
        list.add(g);
        em.persist(g);

        Goal g2 = new Goal();
        g2.setGoalTime(new Date(r.nextInt(15000)));
        list.add(g2);
        em.persist(g2);

        em.getTransaction().commit();

        List<Goal> goalsFromDB = (List<Goal>) gdaoi.findAll();

        assertEquals(list, goalsFromDB);
    }
}
