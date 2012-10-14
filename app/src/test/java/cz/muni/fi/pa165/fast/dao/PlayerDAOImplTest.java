/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.dao;


import cz.muni.fi.pa165.fast.dao.impl.PlayerDAOImpl;
import cz.muni.fi.pa165.fast.dao.impl.TeamDAOImpl;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Peter Laurencik
 */
public class PlayerDAOImplTest {
    
    private static EntityManagerFactory emf;
    private static PlayerDAO playerDAO;
    
    @BeforeClass
    public static void setUpClass()
    {
        emf = Persistence.createEntityManagerFactory("FAST-TestPU");
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        emf.close();
    }
    
    @Before
    public static void setUp()
    {
        playerDAO = new PlayerDAOImpl();
        playerDAO.setEntityManagerFactory(emf);
    }
    
    @After
    public static void tearDown(){}
    
    @Test
    public void createTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.create(player);
            Assert.fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
       
        player = new Player();
        
        try{
            playerDAO.create(player);
        }catch(Exception ex)
        {
            Assert.fail("Error when player is created.");
        } 
        
        Player player2 = new Player();
        player2.setId(null);
        try
        {
            playerDAO.create(player2);
            Assert.fail("Error when player with null id is created");
        }catch(IllegalArgumentException ex)
        {
            //OK
        }
    }
    
    @Test
    public void updateTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.update(player);
            Assert.fail("Null pointer exception shoud be thrown whne null player updating.");
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
            Assert.fail("Error when player is updated.");
        }         
    }
    
    @Test
    public void deleteTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.delete(player);
            Assert.fail("Null pointer exception shoud be thrown whne null player creating.");
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
            Assert.fail("Error when player is deleted.");
        }         
    }
    
    @Test
    public void findAlltest()
    {
        Player player1 = new Player();
        Player player2 = new Player();
        
        playerDAO.create(player1);
        playerDAO.create(player2);
        
        Collection<Player> players = playerDAO.findAll();
        
        Assert.assertEquals(2, players.size());
        
        Assert.assertTrue(players.contains(player1));
        Assert.assertTrue(players.contains(player2));
        
    }
    
    @Test
    public void findPlayersByTeam()
    {
        Team team = new Team();
        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> players = team.getPlayers();
        
        
        players.add(player1);
        players.add(player2);
        
        team.setPlayers(players);
        players = playerDAO.findPlayersByTeam(team);
        Assert.assertTrue(players.contains(player1));
        Assert.assertTrue(players.contains(player2));
        
        
    }
    
    @Test
    public void getPlayerByScoredGoalTest()
    {
        try{
            playerDAO.getPlayerByScoredGoal(null);
        }catch(UnsupportedOperationException ex){
            Assert.fail("Does not implement.");
        }
    }
    
    @Test
    public void getPlayerByAssistedGoalTest()
    {
        try{
            playerDAO.getPlayerByAssistedGoal(null);
        }catch(UnsupportedOperationException ex){
            Assert.fail("Does not implement.");
        }
    }
}
