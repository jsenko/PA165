package cz.muni.fi.pa165.fast.service;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import cz.muni.fi.pa165.fast.convert.TeamConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchResult;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.impl.TeamServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceImplTest {

    @Mock
    private TeamDAO teamDaoMock;
    @Mock
    private MatchDAO matchDaoMock;
    @Mock
    private PlayerDAO playerDaoMock;
    @Mock
    private TeamConvert converterMock;
    private TeamService service;
    private JavaEEGloss gloss;

    public TeamServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gloss = new JavaEEGloss();
        gloss.addEJB(teamDaoMock);
        gloss.addEJB(matchDaoMock);
        gloss.addEJB(playerDaoMock);
        service = gloss.make(TeamServiceImpl.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createTeam() {
        TeamDTO dto = new TeamDTO();
        dto.setName("TeamName");
        Team entity = new Team();
        entity.setName("TeamName");

        service.create(dto);

        verify(teamDaoMock).create(entity);
        verifyNoMoreInteractions(teamDaoMock);
    }

    @Test
    public void updateTeam() {
        TeamDTO dto = new TeamDTO();
        dto.setName("TeamName");
        Team entity = new Team();
        entity.setName("TeamName");

        service.update(dto);

        verify(teamDaoMock).update(entity);
        verifyNoMoreInteractions(teamDaoMock);
    }

    @Test
    public void deleteTeam() {
        TeamDTO dto = new TeamDTO();
        dto.setName("TeamName");
        Team entity = new Team();
        entity.setName("TeamName");

        service.delete(dto);

        verify(teamDaoMock).delete(entity);
        verifyNoMoreInteractions(teamDaoMock);
    }

    @Test
    public void findAll() {
        //********Entity preparation*******//
        Team teamA = new Team();
        Team teamB = new Team();
        Team teamC = new Team();

        Match matchA1 = new Match();
        Match matchA2 = new Match();
        Match matchA3 = new Match();
        Match matchA4 = new Match();
        Match matchA5 = new Match();
        Match matchB1 = new Match();
        Match matchB2 = new Match();
        Match matchB3 = new Match();
        Match matchB4 = new Match();
        Match matchB5 = new Match();
        Match matchC1 = new Match();
        Match matchC2 = new Match();
        Match matchC3 = new Match();
        Match matchC4 = new Match();
        Match matchC5 = new Match();

        Player playerA = new Player();
        Player playerB = new Player();
        Player playerC = new Player();

        teamA.setId(5l);
        teamA.setName("TeamA");
        teamA.setPlayers(new ArrayList<Player>());
        teamA.getPlayers().add(playerA);
        teamA.setAwayMatches(new ArrayList<Match>());
        teamA.setHomeMatches(new ArrayList<Match>());
        teamA.getAwayMatches().add(matchA1);
        teamA.getAwayMatches().add(matchA2);
        teamA.getAwayMatches().add(matchA3);
        teamA.getHomeMatches().add(matchA4);
        teamA.getHomeMatches().add(matchA5);

        teamB.setId(6l);
        teamB.setName("TeamB");
        teamB.setPlayers(new ArrayList<Player>());
        teamB.getPlayers().add(playerB);
        teamB.setAwayMatches(new ArrayList<Match>());
        teamB.setHomeMatches(new ArrayList<Match>());
        teamB.getAwayMatches().add(matchB1);
        teamB.getAwayMatches().add(matchB2);
        teamB.getAwayMatches().add(matchB3);
        teamB.getHomeMatches().add(matchB4);
        teamB.getHomeMatches().add(matchB5);

        teamC.setId(7l);
        teamC.setName("TeamC");
        teamC.setPlayers(new ArrayList<Player>());
        teamC.getPlayers().add(playerC);
        teamC.setAwayMatches(new ArrayList<Match>());
        teamC.setHomeMatches(new ArrayList<Match>());
        teamC.getAwayMatches().add(matchC1);
        teamC.getAwayMatches().add(matchC2);
        teamC.getAwayMatches().add(matchC3);
        teamC.getHomeMatches().add(matchC4);
        teamC.getHomeMatches().add(matchC5);


        Date matchDate = new Date(System.currentTimeMillis() - 50000);

        //2:1 won
        matchA1.setId(10l);
        matchA1.setMatchDate(new Date(matchDate.getTime() + 1000));
        matchA1.setAwayTeam(teamA);
        matchA1.setGoals(new ArrayList<Goal>());
        Goal goal = new Goal();
        goal.setMatch(matchA1);
        goal.setScorePlayer(playerA);
        matchA1.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA1);
        goal.setScorePlayer(playerA);
        matchA1.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA1);
        matchA1.getGoals().add(goal);

        //1:6 lost
        matchA3.setId(20l);
        matchA3.setMatchDate(new Date(matchDate.getTime() + 2000));
        matchA3.setAwayTeam(teamA);
        matchA3.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA3);
        goal.setScorePlayer(playerA);
        matchA3.getGoals().add(goal);
        for (int i = 0; i < 6; i++) {
            goal = new Goal();
            goal.setMatch(matchA3);
            matchA3.getGoals().add(goal);
        }

        //1:1
        matchA4.setId(30l);
        matchA4.setMatchDate(new Date(matchDate.getTime() + 3000));
        matchA4.setHomeTeam(teamA);
        matchA4.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA4);
        goal.setScorePlayer(playerA);
        matchA4.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA4);
        matchA4.getGoals().add(goal);

        //2:2
        matchA5.setId(40l);
        matchA5.setMatchDate(new Date(matchDate.getTime() + 4000));
        matchA5.setHomeTeam(teamA);
        matchA5.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA5);
        goal.setScorePlayer(playerA);
        matchA5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA5);
        matchA5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA5);
        goal.setScorePlayer(playerA);
        matchA5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA5);
        matchA5.getGoals().add(goal);

        //1:0 won
        matchA2.setId(50l);
        matchA2.setMatchDate(new Date(matchDate.getTime() + 5000));
        matchA2.setAwayTeam(teamA);
        matchA2.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA2);
        goal.setScorePlayer(playerA);
        matchA2.getGoals().add(goal);


        //1:1
        matchB4.setId(100l);
        matchB4.setMatchDate(new Date(matchDate.getTime() + 1000));
        matchB4.setHomeTeam(teamB);
        matchB4.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchB4);
        goal.setScorePlayer(playerB);
        matchB4.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchB4);
        matchB4.getGoals().add(goal);

        //2:2
        matchB5.setId(200l);
        matchB5.setMatchDate(new Date(matchDate.getTime() + 2000));
        matchB5.setHomeTeam(teamB);
        matchB5.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchB5);
        goal.setScorePlayer(playerB);
        matchB5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchB5);
        matchB5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchB5);
        goal.setScorePlayer(playerB);
        matchB5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchB5);
        matchB5.getGoals().add(goal);

        //4:0 lost
        matchB1.setId(300l);
        matchB1.setMatchDate(new Date(matchDate.getTime() + 3000));
        matchB1.setAwayTeam(teamB);
        matchB1.setGoals(new ArrayList<Goal>());
        for (int i = 0; i < 4; i++) {
            goal = new Goal();
            goal.setMatch(matchB1);
            matchB1.getGoals().add(goal);
        }

        //2:0 lost
        matchB2.setId(400l);
        matchB2.setMatchDate(new Date(matchDate.getTime() + 4000));
        matchB2.setAwayTeam(teamB);
        matchB2.setGoals(new ArrayList<Goal>());
        for (int i = 0; i < 2; i++) {
            goal = new Goal();
            goal.setMatch(matchB2);
            matchB2.getGoals().add(goal);
        }

        //3:0 lost
        matchB3.setId(500l);
        matchB3.setMatchDate(new Date(matchDate.getTime() + 5000));
        matchB3.setAwayTeam(teamB);
        matchB3.setGoals(new ArrayList<Goal>());
        for (int i = 0; i < 3; i++) {
            goal = new Goal();
            goal.setMatch(matchB3);
            matchB3.getGoals().add(goal);
        }


        //1:0 won
        matchC1.setId(1000l);
        matchC1.setMatchDate(new Date(matchDate.getTime() + 1000));
        matchC1.setAwayTeam(teamC);
        matchC1.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchC1);
        goal.setScorePlayer(playerC);
        matchC1.getGoals().add(goal);

        //2:0 won
        matchC2.setId(2000l);
        matchC2.setMatchDate(new Date(matchDate.getTime() + 2000));
        matchC2.setAwayTeam(teamC);
        matchC2.setGoals(new ArrayList<Goal>());
        for (int i = 0; i < 2; i++) {
            goal = new Goal();
            goal.setMatch(matchC2);
            goal.setScorePlayer(playerC);
            matchC2.getGoals().add(goal);
        }

        //3:3
        matchC5.setId(3000l);
        matchC5.setMatchDate(new Date(matchDate.getTime() + 3000));
        matchC5.setHomeTeam(teamC);
        matchC5.setGoals(new ArrayList<Goal>());
        for (int i = 0; i < 3; i++) {
            goal = new Goal();
            goal.setMatch(matchC5);
            goal.setScorePlayer(playerC);
            matchC5.getGoals().add(goal);
        }
        for (int i = 0; i < 3; i++) {
            goal = new Goal();
            goal.setMatch(matchC5);
            matchC5.getGoals().add(goal);
        }

        //3:0 won
        matchC3.setId(4000l);
        matchC3.setMatchDate(new Date(matchDate.getTime() + 4000));
        matchC3.setAwayTeam(teamC);
        matchC3.setGoals(new ArrayList<Goal>());
        for (int i = 0; i < 3; i++) {
            goal = new Goal();
            goal.setMatch(matchC3);
            goal.setScorePlayer(playerC);
            matchC3.getGoals().add(goal);
        }

        //1:0 won
        matchC4.setId(5000l);
        matchC4.setMatchDate(new Date(matchDate.getTime() + 5000));
        matchC4.setHomeTeam(teamC);
        matchC4.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchC4);
        goal.setScorePlayer(playerC);
        matchC4.getGoals().add(goal);


        playerA.setId(111l);
        playerA.setTeam(teamA);
        playerB.setId(222l);
        playerB.setTeam(teamB);
        playerC.setId(333l);
        playerC.setTeam(teamC);


        List<Team> teamList = new ArrayList<Team>();
        teamList.add(teamA);
        teamList.add(teamB);
        teamList.add(teamC);

        List<Match> matchAAwayList = new ArrayList<Match>();
        matchAAwayList.add(matchA1);
        matchAAwayList.add(matchA2);
        matchAAwayList.add(matchA3);

        List<Match> matchBAwayList = new ArrayList<Match>();
        matchBAwayList.add(matchB1);
        matchBAwayList.add(matchB2);
        matchBAwayList.add(matchB3);

        List<Match> matchCAwayList = new ArrayList<Match>();
        matchCAwayList.add(matchC1);
        matchCAwayList.add(matchC2);
        matchCAwayList.add(matchC3);

        List<Match> matchAHomeList = new ArrayList<Match>();
        matchAHomeList.add(matchA4);
        matchAHomeList.add(matchA5);

        List<Match> matchBHomeList = new ArrayList<Match>();
        matchBHomeList.add(matchB4);
        matchBHomeList.add(matchB5);

        List<Match> matchCHomeList = new ArrayList<Match>();
        matchCHomeList.add(matchC4);
        matchCHomeList.add(matchC5);

        //********Actual testing********//

        when(teamDaoMock.findAll()).thenReturn(teamList);
        when(matchDaoMock.findByAwayTeam(teamA)).thenReturn(matchAAwayList);
        when(matchDaoMock.findByAwayTeam(teamB)).thenReturn(matchBAwayList);
        when(matchDaoMock.findByAwayTeam(teamC)).thenReturn(matchCAwayList);
        when(matchDaoMock.findByHomeTeam(teamA)).thenReturn(matchAHomeList);
        when(matchDaoMock.findByHomeTeam(teamB)).thenReturn(matchBHomeList);
        when(matchDaoMock.findByHomeTeam(teamC)).thenReturn(matchCHomeList);

        List<TeamDTO> dtos = service.findAll();

        verify(teamDaoMock).findAll();
        verify(matchDaoMock).findByAwayTeam(teamA);
        verify(matchDaoMock).findByAwayTeam(teamB);
        verify(matchDaoMock).findByAwayTeam(teamC);
        verify(matchDaoMock).findByHomeTeam(teamA);
        verify(matchDaoMock).findByHomeTeam(teamB);
        verify(matchDaoMock).findByHomeTeam(teamC);
        verifyNoMoreInteractions(teamDaoMock);
        verifyNoMoreInteractions(matchDaoMock);

        assertEquals(3, dtos.size());

        TeamDTO team = dtos.get(0);
        assertEquals(7, team.getId());
        assertEquals("TeamC", team.getName());
        assertEquals(9, team.getPoints());
        assertEquals(10, team.getGoalsFor());
        assertEquals(3, team.getGoalsAgainst());
        assertEquals(4, team.getWon());
        assertEquals(0, team.getLost());
        assertEquals(1, team.getDrawn());
        MatchResult[] trend = team.getTrend();
        assertEquals(trend[0], MatchResult.WON);
        assertEquals(trend[1], MatchResult.WON);
        assertEquals(trend[2], MatchResult.DRAWN);
        assertEquals(trend[3], MatchResult.WON);
        assertEquals(trend[4], MatchResult.WON);

        team = dtos.get(1);
        assertEquals(5, team.getId());
        assertEquals("TeamA", team.getName());
        assertEquals(6, team.getPoints());
        assertEquals(7, team.getGoalsFor());
        assertEquals(10, team.getGoalsAgainst());
        assertEquals(2, team.getWon());
        assertEquals(1, team.getLost());
        assertEquals(2, team.getDrawn());
        trend = team.getTrend();
        assertEquals(trend[0], MatchResult.WON);
        assertEquals(trend[1], MatchResult.LOST);
        assertEquals(trend[2], MatchResult.DRAWN);
        assertEquals(trend[3], MatchResult.DRAWN);
        assertEquals(trend[4], MatchResult.WON);

        team = dtos.get(2);
        assertEquals(6, team.getId());
        assertEquals("TeamB", team.getName());
        assertEquals(2, team.getPoints());
        assertEquals(3, team.getGoalsFor());
        assertEquals(12, team.getGoalsAgainst());
        assertEquals(0, team.getWon());
        assertEquals(3, team.getLost());
        assertEquals(2, team.getDrawn());
        trend = team.getTrend();
        assertEquals(trend[0], MatchResult.DRAWN);
        assertEquals(trend[1], MatchResult.DRAWN);
        assertEquals(trend[2], MatchResult.LOST);
        assertEquals(trend[3], MatchResult.LOST);
        assertEquals(trend[4], MatchResult.LOST);
    }

    @Test
    public void findByPlayer() {
        Team teamA = new Team();

        Match matchA1 = new Match();
        Match matchA2 = new Match();
        Match matchA3 = new Match();
        Match matchA4 = new Match();
        Match matchA5 = new Match();

        Player playerA = new Player();

        teamA.setId(5l);
        teamA.setName("TeamA");
        teamA.setPlayers(new ArrayList<Player>());
        teamA.getPlayers().add(playerA);
        teamA.setAwayMatches(new ArrayList<Match>());
        teamA.setHomeMatches(new ArrayList<Match>());
        teamA.getAwayMatches().add(matchA1);
        teamA.getAwayMatches().add(matchA2);
        teamA.getAwayMatches().add(matchA3);
        teamA.getHomeMatches().add(matchA4);
        teamA.getHomeMatches().add(matchA5);

        Date matchDate = new Date(System.currentTimeMillis() - 50000);

        //2:1 won
        matchA1.setId(10l);
        matchA1.setMatchDate(new Date(matchDate.getTime() + 1000));
        matchA1.setAwayTeam(teamA);
        matchA1.setGoals(new ArrayList<Goal>());
        Goal goal = new Goal();
        goal.setMatch(matchA1);
        goal.setScorePlayer(playerA);
        matchA1.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA1);
        goal.setScorePlayer(playerA);
        matchA1.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA1);
        matchA1.getGoals().add(goal);

        //1:6 lost
        matchA3.setId(20l);
        matchA3.setMatchDate(new Date(matchDate.getTime() + 2000));
        matchA3.setAwayTeam(teamA);
        matchA3.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA3);
        goal.setScorePlayer(playerA);
        matchA3.getGoals().add(goal);
        for (int i = 0; i < 6; i++) {
            goal = new Goal();
            goal.setMatch(matchA3);
            matchA3.getGoals().add(goal);
        }

        //1:1
        matchA4.setId(30l);
        matchA4.setMatchDate(new Date(matchDate.getTime() + 3000));
        matchA4.setHomeTeam(teamA);
        matchA4.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA4);
        goal.setScorePlayer(playerA);
        matchA4.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA4);
        matchA4.getGoals().add(goal);

        //2:2
        matchA5.setId(40l);
        matchA5.setMatchDate(new Date(matchDate.getTime() + 4000));
        matchA5.setHomeTeam(teamA);
        matchA5.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA5);
        goal.setScorePlayer(playerA);
        matchA5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA5);
        matchA5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA5);
        goal.setScorePlayer(playerA);
        matchA5.getGoals().add(goal);
        goal = new Goal();
        goal.setMatch(matchA5);
        matchA5.getGoals().add(goal);

        //1:0 won
        matchA2.setId(50l);
        matchA2.setMatchDate(new Date(matchDate.getTime() + 5000));
        matchA2.setAwayTeam(teamA);
        matchA2.setGoals(new ArrayList<Goal>());
        goal = new Goal();
        goal.setMatch(matchA2);
        goal.setScorePlayer(playerA);
        matchA2.getGoals().add(goal);

        playerA.setId(111l);
        playerA.setTeam(teamA);

        List<Match> matchAAwayList = new ArrayList<Match>();
        matchAAwayList.add(matchA1);
        matchAAwayList.add(matchA2);
        matchAAwayList.add(matchA3);

        List<Match> matchAHomeList = new ArrayList<Match>();
        matchAHomeList.add(matchA4);
        matchAHomeList.add(matchA5);

        when(matchDaoMock.findByAwayTeam(teamA)).thenReturn(matchAAwayList);
        when(matchDaoMock.findByHomeTeam(teamA)).thenReturn(matchAHomeList);
        when(playerDaoMock.getById(111l)).thenReturn(playerA);
        when(teamDaoMock.findTeamByPlayer(playerA)).thenReturn(teamA);

        TeamDTO team = service.findByPlayer(111l);

        verify(matchDaoMock).findByAwayTeam(teamA);
        verify(matchDaoMock).findByHomeTeam(teamA);
        verify(playerDaoMock).getById(111l);
        verify(teamDaoMock).findTeamByPlayer(playerA);
        verifyNoMoreInteractions(teamDaoMock);
        verifyNoMoreInteractions(matchDaoMock);
        verifyNoMoreInteractions(playerDaoMock);

        assertEquals(5, team.getId());
        assertEquals("TeamA", team.getName());
        assertEquals(6, team.getPoints());
        assertEquals(7, team.getGoalsFor());
        assertEquals(10, team.getGoalsAgainst());
        assertEquals(2, team.getWon());
        assertEquals(1, team.getLost());
        assertEquals(2, team.getDrawn());
        MatchResult[] trend = team.getTrend();
        assertEquals(trend[0], MatchResult.WON);
        assertEquals(trend[1], MatchResult.LOST);
        assertEquals(trend[2], MatchResult.DRAWN);
        assertEquals(trend[3], MatchResult.DRAWN);
        assertEquals(trend[4], MatchResult.WON);
    }
}
