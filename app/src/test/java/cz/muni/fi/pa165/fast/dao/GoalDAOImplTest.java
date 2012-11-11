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
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
    public static void tearDownClass() throws NamingException{
        context.close();
    }

    @Before
    public void setUp(){
     
    }

    @After
    public void tearDown() {
        
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
    }

    /**
     * Test of update method, of class GoalDAOImpl.
     */
    @Test
    public void testUpdate() {
        Goal goal = new Goal();

        em.persist(goal);

        long milis = 895465778;
        goal.setGoalTime(new Date(milis));

        gdaoi.update(goal);

        Goal goalFromDB = em.find(Goal.class, goal.getId());

        assertEquals(milis, goalFromDB.getGoalTime().getTime());

        em.remove(goal);
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
    public void testFindAll() {
        Random r = new Random();

        List<Goal> list = new LinkedList<Goal>();

        Goal g = new Goal();

        g.setGoalTime(new Date(r.nextInt(15000)));
        list.add(g);
        em.persist(g);

        Goal g2 = new Goal();
        g2.setGoalTime(new Date(r.nextInt(15000)));
        list.add(g2);
        em.persist(g2);


        List<Goal> goalsFromDB = (List<Goal>) gdaoi.findAll();

        assertEquals(list, goalsFromDB);
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
