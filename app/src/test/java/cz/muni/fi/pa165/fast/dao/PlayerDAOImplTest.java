/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.dao;



import cz.muni.fi.pa165.fast.dao.impl.PlayerDAOImpl;
import cz.muni.fi.pa165.fast.model.Player;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Peter Laurencik
 */
public class PlayerDAOImplTest {
    
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    private static PlayerDAO playerDAO;

    @Before
    public void setUp()
    {
        playerDAO = new PlayerDAOImpl();
    }
    
    @Test
    public void createTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.create(player);
            fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
       
        player = new Player();
        player.setName("Mike");
        
        try{
            playerDAO.create(player);
        }catch(Exception ex)
        {
            fail("Error when player is created.");
        } 
    }
    
    @Test
    public void updateTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.update(player);
            fail("Null pointer exception shoud be thrown whne null player updating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
       
        player = new Player();
        
        try{
            playerDAO.create(player);
            player.setAge(24);;
            playerDAO.update(player);
        }catch(Exception ex)
        {
            fail("Error when player is updated.");
        }         
    }
    
    @Test
    public void deleteTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.delete(player);
            fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
       
        player = new Player();
        
        try{
            playerDAO.create(player);
            playerDAO.delete(player);
            
        }catch(Exception ex)
        {
            fail("Error when player is deleted.");
        }         
    }
    
    @Test
    public void findAlltest()
    {
        Player player1 = new Player();
        player1.setName("Mike");
        Player player2 = new Player();
        player2.setName("Felix");
        
        playerDAO.create(player1);
        playerDAO.create(player2);
        
        Collection<Player> allPlayers = playerDAO.findAll();
        
        Assert.assertEquals(2, allPlayers.size());
        
        Assert.assertTrue(allPlayers.contains(player1));
        Assert.assertTrue(allPlayers.contains(player2));
        
    }
    
       
    @Test
    public void getPlayerByScoredGoalTest()
    {
        try{
            playerDAO.getPlayerByScoredGoal(null);
            fail();
        }catch(UnsupportedOperationException ex){
            // ok
        }
    }
    
    @Test
    public void getPlayerByAssistedGoalTest()
    {
        try{
            playerDAO.getPlayerByAssistedGoal(null);
            fail();
        }catch(UnsupportedOperationException ex){
            // ok
        }
    }
}
