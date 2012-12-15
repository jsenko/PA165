package cz.muni.fi.pa165.fast.service;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.convert.impl.MatchConvertImpl;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.impl.MatchServiceImpl;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class MatchServiceImplTest {

    MatchDAO matchDAOMock;
    TeamDAO teamDAOMock;
    GoalDAO goalDAOMock;
    MatchConvert convert;
    MatchService service;

    @Before
    public void setUp() {
        MockData mdata = new MockData();
        mdata.mock();
        matchDAOMock = mdata.getMatchDAOMock();
        teamDAOMock = mdata.getTeamDAOMock();
        goalDAOMock = mdata.getGoalDAOMock();

        JavaEEGloss gloss = new JavaEEGloss();
        gloss.addEJB(teamDAOMock);
        gloss.addEJB(goalDAOMock);
        convert = gloss.make(MatchConvertImpl.class);

        gloss = new JavaEEGloss();
        gloss.addEJB(matchDAOMock);
        gloss.addEJB(teamDAOMock);
        gloss.addEJB(convert);
        service = gloss.make(MatchServiceImpl.class);
    }

    public void crudPrepare(MatchDTO dto, Match m) {
        dto.setId(1);
        dto.setHomeTeamId(2);
        dto.setAwayTeamId(3);

        Team t1 = new Team();
        t1.setId(2L);
        Team t2 = new Team();
        t2.setId(3L);

        m.setId(1L);

        doReturn(t1).when(teamDAOMock).getById(2L);
        doReturn(t2).when(teamDAOMock).getById(3L);
    }

    public void crudVerify() {
        verifyNoMoreInteractions(matchDAOMock);
        verify(teamDAOMock).getById(2L);
        verify(teamDAOMock).getById(3L);
        verifyNoMoreInteractions(teamDAOMock);
    }

    @Test
    public void create() {
        try {
            service.create(null);
            fail();
        } catch (Exception e) { /* ok */ }

        Match m = new Match();
        MatchDTO dto = new MatchDTO();

        crudPrepare(dto, m);

        service.create(dto);

        verify(matchDAOMock).create(m);
        crudVerify();
    }

    @Test
    public void update() {
        try {
            service.create(null);
            fail();
        } catch (Exception e) { /* ok */ }

        Match m = new Match();
        MatchDTO dto = new MatchDTO();

        crudPrepare(dto, m);

        service.update(dto);

        verify(matchDAOMock).update(m);
        crudVerify();
    }

    @Test
    public void delete() {
        try {
            service.create(null);
            fail();
        } catch (Exception e) { /* ok */ }

        Match m = new Match();
        MatchDTO dto = new MatchDTO();

        crudPrepare(dto, m);

        service.delete(dto);

        verify(matchDAOMock).delete(m);
        verifyNoMoreInteractions(matchDAOMock);
        verifyZeroInteractions(teamDAOMock);
    }

    @Test
    public void findAll() {
        List<MatchDTO> dtos = service.findAll();

        assertEquals(6, dtos.size());

        verify(matchDAOMock).findAll();
        verifyNoMoreInteractions(matchDAOMock);

        MatchDTO dto = dtos.get(4); // get sample - fifth match, ordered by date
        assertEquals(5, dto.getId());
        assertEquals(2, dto.getRound());
        assertEquals(3, dto.getHomeTeamId());
        assertEquals("Third Team", dto.getHomeTeamName());
        assertEquals(1, dto.getAwayTeamId());
        assertEquals("First Team", dto.getAwayTeamName());
        assertEquals(1, (int) dto.getHomeTeamGoals());
        assertEquals(0, (int) dto.getAwayTeamGoals());
    }

    @Test
    public void testGetById() {
        Match m1 = new Match();
        m1.setId(1L);

        MatchDTO m = service.getById(1L);

        verify(matchDAOMock).getById(1L);
        verifyNoMoreInteractions(matchDAOMock);

        assertEquals(m.getId(), m1.getId().longValue());

        try {
            service.getById(0L);
            fail();
        } catch (Exception ex) {
            //OK
        }
    }
}
