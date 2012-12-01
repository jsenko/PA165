package cz.muni.fi.pa165.fast.service;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import cz.muni.fi.pa165.fast.convert.PlayerConvert;
import cz.muni.fi.pa165.fast.convert.impl.PlayerConvertImpl;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.service.impl.PlayerServiceImpl;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Peter Laurencik
 */

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {
    
    
    private PlayerDAO playerDao;
    
    private GoalDAO goalDao;
    
    private PlayerConvert convert;
    
    private PlayerService service;
    
    @Before
    public void setUp()
    {
        MockData mData = new MockData();
        mData.mock();
        
        playerDao = mData.getPlayerDAOMock();
        goalDao = mData.getGoalDAOMock();
        
        JavaEEGloss gloss = new JavaEEGloss();
        gloss.addEJB(playerDao);
        gloss.addEJB(goalDao);
        
        convert = gloss.make(PlayerConvertImpl.class);
        
        gloss = new JavaEEGloss();
        gloss.addEJB(playerDao);
        gloss.addEJB(goalDao);
        gloss.addEJB(convert);
        
        service = gloss.make(PlayerServiceImpl.class);
        
    }
    
    @After
    public void tearDown()
    {}
    
    @Test
    public void create()
    {
        
        PlayerDTO dto = new PlayerDTO();
        dto.setName("Fin");
        dto.setId(1L);
        
        Player player = new Player();
        player.setName("Fin");
        
        Long playerId = service.create(dto);
        
        player.setId(playerId);
        
        verify(playerDao).create(player);
        verifyNoMoreInteractions(playerDao);
        
        
    }
    
    @Test
    public void update()
    {
        PlayerDTO dto = new PlayerDTO();
        dto.setName("Robin");
        dto.setId(1L);
        
        Player player = new Player();
        player.setName("Robin");
        
        player.setId(service.update(dto));
        
        verify(playerDao).update(player);
        verifyNoMoreInteractions(playerDao);
        
    }
    
    @Test
    public void delete()
    {
        PlayerDTO dto = new PlayerDTO();
        dto.setName("Kayle");
        
        Player player = convert.fromDTOToEntity(dto);
        
        service.delete(dto);
        
        verify(playerDao).delete(player);
        verifyNoMoreInteractions(playerDao);
        
        
    }
    
    @Test
    public void findAll()
    {
  
        List<PlayerDTO> playersDtoByName = service.findAll(PlayerOrderBy.NAME);
        List<PlayerDTO> playersDtoByGoals = service.findAll(PlayerOrderBy.GOALS);
        List<PlayerDTO> playersDtoByAge = service.findAll(PlayerOrderBy.AGE);
        List<PlayerDTO> playersDtoByHeight = service.findAll(PlayerOrderBy.HEIGHT);
        List<PlayerDTO> playersDtoByWeight = service.findAll(PlayerOrderBy.WEIGHT);
        
        PlayerDTO firstByNamePlayer = playersDtoByName.get(0);
        PlayerDTO lastByNamePlayer = playersDtoByName.get(8);
        
        PlayerDTO firstByGoalsPlayer = playersDtoByGoals.get(0);
        PlayerDTO lastByGoalsPlayer = playersDtoByGoals.get(8);
        
        PlayerDTO youngestPlayer = playersDtoByAge.get(0);
        PlayerDTO oldestPlayer = playersDtoByAge.get(8);
        
        PlayerDTO lightestPlayer = playersDtoByHeight.get(0);
        PlayerDTO heaviestPlayer = playersDtoByHeight.get(8);
        
        PlayerDTO smallestPlayer = playersDtoByWeight.get(0);
        PlayerDTO tallestPlayer = playersDtoByWeight.get(8);
        
        
        assertEquals(firstByNamePlayer.getId(), 6L);
        assertEquals(lastByNamePlayer.getId(), 1L);
        
        assertEquals(firstByGoalsPlayer.getId(), 1L);
        assertEquals(lastByGoalsPlayer.getId(), 9L);
        
        assertEquals(youngestPlayer.getId(), 8L);
        assertEquals(oldestPlayer.getId(), 6L);
        
        assertEquals(lightestPlayer.getId(), 5L);
        assertEquals(heaviestPlayer.getId(), 9L);
        
        assertEquals(smallestPlayer.getId(), 7L);
        assertEquals(tallestPlayer.getId(), 5L);
        
        
        
        

    }
}


