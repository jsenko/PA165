package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Peter Laurencik
 */
public class PlayerDAOImplTest {
    
    private static Context context;
    private static PlayerDAO playerDAO;
    private static PlayerFakeEntityManager fem;

    @BeforeClass
    public static void setUpClass() throws Exception {
        context = EJBContainer.createEJBContainer().getContext();
        playerDAO = (PlayerDAO) context.lookup("java:global/app/PlayerDAOImpl");
        fem = (PlayerFakeEntityManager) context.lookup("java:global/app/PlayerFakeEntityManager");
    }

    @AfterClass
    public static void setDownClass() throws NamingException {
        context.close();
    }
    
    @Test
    public void createNullTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.create(player);
            fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(EJBException ex)
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
            playerDAO.create(player);
        }catch(Exception ex)
        {
            fail("Error when player is created.");
        } 
        
        Player foundPlayer = fem.find(Player.class, player.getId());
        
        Assert.assertEquals(player, foundPlayer);
    }
    
    @Test
    public void updateNullTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.update(player);
            
            fail("Null pointer exception shoud be thrown when null player updating.");
        }catch(EJBException ex)
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
            fem.persist(player);
            
            player.setAge(24);

            playerDAO.update(player);
 
        }catch(EJBException ex)
        {
            fail("Error when player is updated.");
        }  
        
        Player foundPlayer = fem.find(Player.class, player.getId());
        
        Assert.assertEquals(player, foundPlayer);
    }
    
    @Test
    public void deleteNullTest()
    {
        Player player;
        player = null;
        
        try{
            playerDAO.delete(player);
            
            fail("Null pointer exception shoud be thrown whne null player creating.");
        }catch(EJBException ex)
        {
            //OK
        }
    }   
    
    @Test
    public void deleteTest()
    {  
        Player player = new Player();
        
        try{
            fem.persist(player);

            playerDAO.delete(player);
            
        }catch(Exception ex)
        {
            fail("Error when player is deleted.");
        }  
        
        Player deletedPlayer = fem.find(Player.class, player.getId());
        
        Assert.assertNull(deletedPlayer);
    }
    
    @Test
    public void findAllTest()
    {
        Player player1 = new Player();
        player1.setName("Mike");
        Player player2 = new Player();
        player2.setName("Felix");
        
        fem.persist(player1);
        fem.persist(player2);
        
        Collection<Player> allPlayers = playerDAO.findAll();
        
        Assert.assertEquals(2, allPlayers.size());
        
        Assert.assertTrue(allPlayers.contains(player1));
        Assert.assertTrue(allPlayers.contains(player2));
        
    }
    
    @Test
    public void findPlayersByTeamTest(){
        Team t = new Team();
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setTeam(t);
        p2.setTeam(t);
        t.setPlayers(new ArrayList<Player>());
        t.getPlayers().add(p1);
        t.getPlayers().add(p2);
        
        fem.persist(t);
        
        List<Player> list = playerDAO.findPlayersByTeam(t);
        
        Assert.assertEquals(2, list.size());
        
        Assert.assertTrue(list.contains(p1));
        Assert.assertTrue(list.contains(p2));
    }
    
    @Stateless
    public static class PlayerFakeEntityManager {

        @PersistenceContext(name = "TestPU")
        private EntityManager em;

        public void remove(Player p) {
            em.remove(em.find(Player.class, p.getId()));
        }

        public void persist(Object o) {
            em.persist(o);
        }

        public Player find(Class<Player> c, Object o) {
            return em.find(c, o);
        }
    }
}
