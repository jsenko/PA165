/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.dao.impl.GoalDAOImpl;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class GoalDAOImplTest {

    private GoalDAOImpl gdaoi;
    private EntityManagerFactory emf;

    public GoalDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TestPU");
        gdaoi = new GoalDAOImpl();
        gdaoi.setEntityManagerFactory(emf);
    }

    @After
    public void tearDown() {
        emf.close();
    }

    /**
     * Test of create method, of class GoalDAOImpl.
     */
    @Test
    public void testCreate() {
        Goal goal = new Goal();
        gdaoi.create(goal);
        assertNotNull(goal.getId());
    }

    /**
     * Test of update method, of class GoalDAOImpl.
     */
    @Test
    public void testUpdate() {
        Goal goal = new Goal();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(goal);
        em.getTransaction().commit();
        em.close();

        long milis = 895465778;
        goal.setGoalTime(new Date(milis));

        gdaoi.update(goal);

        EntityManager em2 = emf.createEntityManager();
        Goal goalFromDB = em2.find(Goal.class, goal.getId());
        em2.close();

        assertEquals(milis, goal.getGoalTime().getTime());

    }

    /**
     * Test of delete method, of class GoalDAOImpl.
     */
    @Test
    public void testDelete() {
        Goal goal = new Goal();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(goal);
        em.getTransaction().commit();
        em.close();

        Long goalId = goal.getId();

        gdaoi.delete(goal);

        EntityManager em2 = emf.createEntityManager();
        Goal goalFromDB = em2.find(Goal.class, goalId);
        assertNull(goalFromDB);
    }

    @Test
    public void testFindAll() {
        Random r = new Random();
        EntityManager em = emf.createEntityManager();

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
        em.close();

    }
    
    
    
}
