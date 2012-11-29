/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.service;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import cz.muni.fi.pa165.fast.convert.GoalConvert;
import cz.muni.fi.pa165.fast.convert.impl.GoalConvertImpl;
import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.service.impl.GoalServiceImpl;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Stefan
 */
public class GoalServiceImplTest {
    
    private GoalDAO goalDaoMock;
    
    private MatchDAO matchDaoMock;
    
    private GoalConvert convert;
    
    private GoalService service;
 
    @Before
    public void setUp() {
        MockData mData = new MockData();
        mData.mock();
        goalDaoMock = mData.getGoalDAOMock();
        matchDaoMock = mData.getMatchDAOMock();
        
        JavaEEGloss gloss = new JavaEEGloss();
        gloss.addEJB(goalDaoMock);
        gloss.addEJB(matchDaoMock);
        
        convert = gloss.make(GoalConvertImpl.class);
        
        gloss = new JavaEEGloss();
        gloss.addEJB(goalDaoMock);
        gloss.addEJB(matchDaoMock);
        gloss.addEJB(convert);
        
        service = gloss.make(GoalServiceImpl.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class GoalServiceImpl.
     */
    @Test
    public void testCreate() {
        Goal goal = new Goal();
        goal.setId(1L);
        
        GoalDTO gDto = new GoalDTO();
        gDto.setId(1L);
        
        service.create(gDto);
        
        verify(goalDaoMock).create(goal);
        verifyNoMoreInteractions(goalDaoMock);
        
        try
        {
            service.create(null);
            fail("Can not create null goal.");
        }catch(Exception ex)
        {
            //ok
        }
  
    }
    
    @Test
    public void testUpdate() {
        Goal goal = new Goal();
        goal.setId(1L);
        
        GoalDTO gDto = new GoalDTO();
        gDto.setId(1L);
        
        service.update(gDto);
        
        verify(goalDaoMock).update(goal);
        verifyNoMoreInteractions(goalDaoMock);
        
        try
        {
            service.update(null);
            fail("Can not update null goal.");
        }catch(Exception ex)
        {
            //ok
        }
  
  
    }
    
    @Test
    public void testDelete() {
        Goal goal = new Goal();
        goal.setId(1L);
        
        GoalDTO gDto = new GoalDTO();
        gDto.setId(1L);
        
        service.delete(gDto);
        
        verify(goalDaoMock).delete(goal);
        verifyNoMoreInteractions(goalDaoMock);
        
        try
        {
            service.delete(null);
            fail("Can not delete null goal.");
        }catch(Exception ex)
        {
            //ok
        }
  
  
    }
    
    @Test
    public void testByMatch() {
        
        List<GoalDTO> goalsByMatch1 = service.findByMatch(1L);
        List<GoalDTO> goalsByMatch2 = service.findByMatch(2L);
        List<GoalDTO> goalsByMatch3 = service.findByMatch(3L);
        List<GoalDTO> goalsByMatch4 = service.findByMatch(4L);
        List<GoalDTO> goalsByMatch5 = service.findByMatch(5L);
        List<GoalDTO> goalsByMatch6 = service.findByMatch(6L);
        
        List<Match> allMatches = (List)matchDaoMock.findAll();
        
        assertEquals(6, allMatches.size());
        //assertEquals(goalsByMatch1.size(), allMatches.get(0).getGoals().size());
        //assertEquals(goalsByMatch2.size(), allMatches.get(1).getGoals().size());
        //assertEquals(goalsByMatch3.size(), allMatches.get(2).getGoals().size());
        //assertEquals(goalsByMatch4.size(), allMatches.get(3).getGoals().size());
        //assertEquals(goalsByMatch5.size(), allMatches.get(4).getGoals().size());
        //assertEquals(goalsByMatch6.size(), allMatches.get(5).getGoals().size());
        
    }
    
    
}
