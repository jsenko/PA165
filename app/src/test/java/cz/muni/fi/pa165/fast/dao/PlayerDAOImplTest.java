/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.dao;



import cz.muni.fi.pa165.fast.dao.impl.PlayerDAOImpl;
import cz.muni.fi.pa165.fast.model.Player;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Peter Laurencik
 */
public class PlayerDAOImplTest {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    private static PlayerDAO playerDAO;

    @Before
    public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        
        
        playerDAO = new PlayerDAOImpl();
        // perform dependency injection
        Field f = playerDAO.getClass().getDeclaredField("em");
        f.setAccessible(true);
        f.set(playerDAO, em);
    }
    
    @After
    public void tearDown() {
    	em.close();
        emf.close();
    }
    
    @Test
    public void createNullTest()
    {
        Player player;
        player = null;
        
        try{
            em.getTransaction().begin();
            playerDAO.create(player);
            em.getTransaction().commit();
            fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
    }
    
    @Test
    public void createTest()
    {
        Player player = new Player();
        player.setName("Mike");
        
        try{
            em.getTransaction().begin();
            playerDAO.create(player);
            em.getTransaction().commit();
        }catch(Exception ex)
        {
            fail("Error when player is created.");
        } 
        
        Player foundPlayer = em.find(Player.class, player.getId());
        
        Assert.assertEquals(player, foundPlayer);
    }
    
    @Test
    public void updateNullTest()
    {
        Player player;
        player = null;
        
        try{
            
            em.getTransaction().begin();
            playerDAO.update(player);
            em.getTransaction().commit();
            
            
            fail("Null pointer exception shoud be thrown whne null player updating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
        
    }
       
    @Test
    public void updateTest()
    {
        Player player = new Player();
        player.setName("Mike");
        
        try{
            em.getTransaction().begin();
            playerDAO.create(player);
            em.getTransaction().commit();
            
            player.setAge(24);

            playerDAO.update(player);
 
        }catch(Exception ex)
        {
            fail("Error when player is updated.");
        }  
        
        Player foundPlayer = em.find(Player.class, player.getId());
        
        Assert.assertEquals(player, foundPlayer);
    }
    
    @Test
    public void deleteNullTest()
    {
        Player player;
        player = null;
        
        try{
            em.getTransaction().begin();
            playerDAO.delete(player);
            em.getTransaction().commit();
            fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
    }   
    
    @Test
    public void deleteTest()
    {  
        Player player = new Player();
        
        try{
            em.getTransaction().begin();
            playerDAO.create(player);
            em.getTransaction().commit();
            playerDAO.delete(player);
            
        }catch(Exception ex)
        {
            fail("Error when player is deleted.");
        }  
        
        Player deletedPlayer = em.find(Player.class, player.getId());
        
        Assert.assertNull(deletedPlayer);
    }
    
    @Test
    public void findAlltest()
    {
        Player player1 = new Player();
        player1.setName("Mike");
        Player player2 = new Player();
        player2.setName("Felix");
        
        em.getTransaction().begin();
        playerDAO.create(player1);
        playerDAO.create(player2);  
        em.getTransaction().commit();
        
        Collection<Player> allPlayers = playerDAO.findAll();
        
        Assert.assertEquals(2, allPlayers.size());
        
        Assert.assertTrue(allPlayers.contains(player1));
        Assert.assertTrue(allPlayers.contains(player2));
        
    }
    
       
    @Test
    public void getPlayerByScoredGoalTest()
    {
        try{
            em.getTransaction().begin();
            playerDAO.getPlayerByScoredGoal(null);
            em.getTransaction().commit();
            fail();
        }catch(UnsupportedOperationException ex){
            // ok
        }
    }
    
    @Test
    public void getPlayerByAssistedGoalTest()
    {
        try{
            em.getTransaction().begin();
            playerDAO.getPlayerByAssistedGoal(null);
            em.getTransaction().commit();
            fail();
        }catch(UnsupportedOperationException ex){
            // ok
        }
    }
}
