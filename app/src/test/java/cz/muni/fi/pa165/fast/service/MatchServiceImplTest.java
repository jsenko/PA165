package cz.muni.fi.pa165.fast.service;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Matchers.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.convert.impl.MatchConvertImpl;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.impl.MatchServiceImpl;


//import static junit.framework.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class MatchServiceImplTest
{
	MatchDAO matchDAOMock;
	
	TeamDAO teamDAOMock;
	
	MatchConvert convert;
	
	MatchService service;
	
	@Before
	public void setUp()
	{
		MockData mdata = new MockData();
		mdata.mock();
		matchDAOMock = mdata.getMatchDAOMock();
		teamDAOMock = mdata.getTeamDAOMock();
		
		JavaEEGloss gloss = new JavaEEGloss();
		gloss.addEJB(teamDAOMock);
		convert = gloss.make(MatchConvertImpl.class);
		
		gloss = new JavaEEGloss();
		gloss.addEJB(matchDAOMock);
		gloss.addEJB(teamDAOMock);
		gloss.addEJB(convert);
		service = gloss.make(MatchServiceImpl.class);
	}
	
	@After
	public void tearDown(){}
	
	
	public void crudPrepare(MatchDTO dto, Match m)
	{
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
	
	
	public void crudVerify()
	{
		verifyNoMoreInteractions(matchDAOMock);
		
		verify(teamDAOMock).getById(2L);
		verify(teamDAOMock).getById(3L);
		verifyNoMoreInteractions(teamDAOMock);
	}
	
	
	@Test
	public void create()
	{
		try
		{
			service.create(null);
			fail();
		}
		catch(Exception e){ /* ok */ }
		
		Match m = new Match();
		MatchDTO dto = new MatchDTO();
		
		crudPrepare(dto, m);
		
		service.create(dto);
		
		verify(matchDAOMock).create(m);
		crudVerify();
	}
	
	@Test
	public void update()
	{
		try
		{
			service.create(null);
			fail();
		}
		catch(Exception e){ /* ok */ }
		
		Match m = new Match();
		MatchDTO dto = new MatchDTO();
		
		crudPrepare(dto, m);
		
		service.update(dto);
		
		verify(matchDAOMock).update(m);
		crudVerify();
	}
	
	@Test
	public void delete()
	{
		try
		{
			service.create(null);
			fail();
		}
		catch(Exception e){ /* ok */ }
		
		Match m = new Match();
		MatchDTO dto = new MatchDTO();
		
		crudPrepare(dto, m);
		
		service.delete(dto);
		
		verify(matchDAOMock).delete(m);
		verifyNoMoreInteractions(matchDAOMock);
		verifyZeroInteractions(teamDAOMock);
	}
	
	
	@Test
	public void findAll()
	{
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
		assertEquals(1, (int)dto.getHomeTeamGoals());
		assertEquals(0, (int)dto.getAwayTeamGoals());
	}
	
	@Test
	public void findByRound()
	{
		List<MatchDTO> dtos = service.findByRound(2);

		assertEquals(3, dtos.size()); // six total, three in each round
		
		verify(matchDAOMock).findAll();
		verifyNoMoreInteractions(matchDAOMock);
		
		// verify that all three matches are from round 2
		for(MatchDTO dto: dtos)
		{
			assertEquals(2, dto.getRound());
		}
		
		MatchDTO dto = dtos.get(0); // get sample - first match in second round
		assertEquals(3, dto.getId());
		assertEquals(2, dto.getRound());
		assertEquals(2, dto.getHomeTeamId());
		assertEquals("Second Team", dto.getHomeTeamName());
		assertEquals(1, dto.getAwayTeamId());
		assertEquals("First Team", dto.getAwayTeamName());
		assertEquals(0, (int)dto.getHomeTeamGoals());
		assertEquals(1, (int)dto.getAwayTeamGoals());
	}
	
	@Test
	public void findByTeam()
	{
		List<MatchDTO> dtos = service.findByTeam(3);

		assertEquals(4, dtos.size()); // four total
		
		verify(matchDAOMock).findByHomeTeam(any(Team.class));
		verify(matchDAOMock).findByAwayTeam(any(Team.class));
		verifyNoMoreInteractions(matchDAOMock);
		
		// verify that all 4 matches have home or away team with id 3
		for(MatchDTO dto: dtos)
		{
			assertTrue(dto.getHomeTeamId() == 3 || dto.getAwayTeamId() == 3);
		}
		
		MatchDTO dto = dtos.get(0); // get sample - match with lowest date
		assertEquals(2, dto.getId());
		assertEquals(1, dto.getRound());
		assertEquals(1, dto.getHomeTeamId());
		assertEquals("First Team", dto.getHomeTeamName());
		assertEquals(3, dto.getAwayTeamId());
		assertEquals("Third Team", dto.getAwayTeamName());
		assertEquals(1, (int)dto.getHomeTeamGoals());
		assertEquals(1, (int)dto.getAwayTeamGoals());
	}
}
