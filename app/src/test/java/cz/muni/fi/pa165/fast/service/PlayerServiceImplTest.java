package cz.muni.fi.pa165.fast.service;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import cz.muni.fi.pa165.fast.convert.PlayerConvert;
import cz.muni.fi.pa165.fast.convert.impl.PlayerConvertImpl;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.service.impl.PlayerServiceImpl;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Peter Laurencik
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

    private PlayerDAO playerDao;
    private GoalDAO goalDao;
    private TeamDAO teamDao;
    private PlayerConvert convert;
    private PlayerService service;

    @Before
    public void setUp() {
        MockData mData = new MockData();
        mData.mock();

        playerDao = mData.getPlayerDAOMock();
        goalDao = mData.getGoalDAOMock();
        teamDao = mData.getTeamDAOMock();

        JavaEEGloss gloss = new JavaEEGloss();
        gloss.addEJB(playerDao);
        gloss.addEJB(goalDao);
        gloss.addEJB(teamDao);

        convert = gloss.make(PlayerConvertImpl.class);

        gloss = new JavaEEGloss();
        gloss.addEJB(playerDao);
        gloss.addEJB(goalDao);
        gloss.addEJB(teamDao);
        gloss.addEJB(convert);

        service = gloss.make(PlayerServiceImpl.class);
    }

    @Test
    public void create() {

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
    public void update() {
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
    public void delete() {
        PlayerDTO dto = new PlayerDTO();
        dto.setName("Kayle");

        Player player = convert.fromDTOToEntity(dto);

        service.delete(dto);

        verify(playerDao).delete(player);
        verifyNoMoreInteractions(playerDao);
    }

    @Test
    public void findAll() {

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
    
    @Test
    public void getById() {
        Player p1 = new Player();
        p1.setId(1L);

        PlayerDTO p = service.getById(1L);

        verify(playerDao).getById(1L);
        verifyNoMoreInteractions(playerDao);

        assertEquals(p.getId(), p1.getId().longValue());

        try {
            service.getById(0L);
            fail();
        } catch (Exception ex) {
            //OK
        }
    }
    
    @Test
    public void findPlayersByTeam(){
        List<PlayerDTO> pOfT1 = service.findPlayersByTeam(1L, PlayerOrderBy.NAME);
        List<PlayerDTO> pOfT2 = service.findPlayersByTeam(2L, PlayerOrderBy.NAME);
        List<PlayerDTO> pOfT3 = service.findPlayersByTeam(3L, PlayerOrderBy.NAME);
        List<PlayerDTO> pOfT4ByName = service.findPlayersByTeam(4L, PlayerOrderBy.NAME);
        List<PlayerDTO> pOfT4ByGoals = service.findPlayersByTeam(4L, PlayerOrderBy.GOALS);
        List<PlayerDTO> pOfT4ByAge = service.findPlayersByTeam(4L, PlayerOrderBy.AGE);
        List<PlayerDTO> pOfT4ByHeight = service.findPlayersByTeam(4L, PlayerOrderBy.HEIGHT);
        List<PlayerDTO> pOfT4ByWight = service.findPlayersByTeam(4L, PlayerOrderBy.WEIGHT);
        
        assertEquals(1, pOfT1.size());
        assertEquals(1, pOfT2.size());
        assertEquals(1, pOfT3.size());
        assertEquals(6, pOfT4ByName.size());
        
        PlayerDTO firstByNamePlayer = pOfT4ByName.get(0);
        PlayerDTO lastByNamePlayer = pOfT4ByName.get(5);

        PlayerDTO firstByGoalsPlayer = pOfT4ByGoals.get(0);
        PlayerDTO lastByGoalsPlayer = pOfT4ByGoals.get(5);

        PlayerDTO youngestPlayer = pOfT4ByAge.get(0);
        PlayerDTO oldestPlayer = pOfT4ByAge.get(5);

        PlayerDTO lightestPlayer = pOfT4ByHeight.get(0);
        PlayerDTO heaviestPlayer = pOfT4ByHeight.get(5);

        PlayerDTO smallestPlayer = pOfT4ByWight.get(0);
        PlayerDTO tallestPlayer = pOfT4ByWight.get(5);

        assertEquals(firstByNamePlayer.getId(), 6L);
        assertEquals(lastByNamePlayer.getId(), 9L);

        assertEquals(youngestPlayer.getId(), 8L);
        assertEquals(oldestPlayer.getId(), 6L);

        assertEquals(lightestPlayer.getId(), 5L);
        assertEquals(heaviestPlayer.getId(), 9L);

        assertEquals(smallestPlayer.getId(), 7L);
        assertEquals(tallestPlayer.getId(), 5L);
    }
}
