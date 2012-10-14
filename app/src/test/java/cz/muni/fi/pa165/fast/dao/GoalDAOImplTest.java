/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.dao.impl.GoalDAOImpl;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Collection;
import java.util.Date;
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
        gdaoi= new GoalDAOImpl();
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
        
        assertEquals(milis,goal.getGoalTime().getTime());
        
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
        Goal goalFromDB = em2.find(Goal.class,goalId);
        assertNull(goalFromDB);
    }

   
}
