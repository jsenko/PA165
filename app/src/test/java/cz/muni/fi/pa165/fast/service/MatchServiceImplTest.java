package cz.muni.fi.pa165.fast.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.impl.MatchServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceImplTest
{
	@Mock
	MatchDAO matchDAOMock;
	
	@Mock
	TeamDAO teamDAOMock;
	
	@Mock
	GoalDAO goalDAOMock;
	
	MatchConvert convert;
	
	MatchService service;
	
	@Before
	public void setUp()
	{
		JavaEEGloss gloss = new JavaEEGloss();
		gloss.addEJB(teamDAOMock);
		gloss.addEJB(goalDAOMock);
		convert = gloss.make(MatchConvert.class);
		
		gloss = new JavaEEGloss();
		gloss.addEJB(matchDAOMock);
		gloss.addEJB(teamDAOMock);
		gloss.addEJB(convert);
		service = gloss.make(MatchServiceImpl.class);
	
	}
	
	@After
	public void tearDown(){}
	
	
	@Test
	public void create()
	{
		try
		{
			service.create(null);
			fail();
		}
		catch(Exception e){ /* ok */ }
		
		MatchDTO dto = new MatchDTO();
		dto.setId(1);
		dto.setHomeTeamId(2);
		dto.setAwayTeamId(3);
		
		Team t1 = new Team();
		t1.setId(2L);
		Team t2 = new Team();
		t2.setId(3L);
		
		Match m = new Match();
		m.setId(1L);
		
		doReturn(t1).when(teamDAOMock).getById(2L);
		doReturn(t2).when(teamDAOMock).getById(3L);
		
		service.create(dto);
		
		verify(matchDAOMock).create(m);
		verifyNoMoreInteractions(matchDAOMock);
		
		verify(teamDAOMock).getById(2L);
		verify(teamDAOMock).getById(3L);
		verifyNoMoreInteractions(teamDAOMock);
		
		verifyZeroInteractions(goalDAOMock);
	}
}
