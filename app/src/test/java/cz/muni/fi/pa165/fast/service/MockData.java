package cz.muni.fi.pa165.fast.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.Mockito;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.PlayerDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Player;
import cz.muni.fi.pa165.fast.model.Team;
import static org.mockito.Mockito.*;

/**
 * Provides DAO mocks with sample data for service layer testing 
 * 
 * @author Jakub Senko
 *
 */
public class MockData
{
	private TeamDAO teamDAOMock = Mockito.mock(TeamDAO.class);

	private MatchDAO matchDAOMock = Mockito.mock(MatchDAO.class);
        
        private GoalDAO goalDAOMock = Mockito.mock(GoalDAO.class);
        
        private PlayerDAO playerDAOMock = Mockito.mock(PlayerDAO.class);

	public void mock()
	{
		// TEAMS
		Team t1 = new Team();
		t1.setId(1L);
		t1.setName("First Team");

		Team t2 = new Team();
		t2.setId(2L);
		t2.setName("Second Team");

		Team t3 = new Team();
		t3.setId(3L);
		t3.setName("Third Team");

		// PLAYERS

		Player p1 = new Player();
                p1.setId(1L);
                p1.setName("Ron");
                p1.setSurname("Owl");
                p1.setWeight(180);
                p1.setHeight(74);
                p1.setAge(21);
                p1.setTeam(t1);

                Player p2 = new Player();
                p2.setId(2L);
                p2.setName("Adam");
                p2.setSurname("Owl");
                p2.setWeight(174);
                p2.setHeight(80);
                p2.setAge(19);
                p2.setTeam(t2);

                Player p3 = new Player();
                p3.setId(3L);
                p3.setName("Denny");
                p3.setSurname("Corny");
                p3.setWeight(184);
                p3.setHeight(79);
                p3.setAge(25);
                p3.setTeam(t3);

                Player p4 = new Player();
                p4.setId(4L);
                p4.setName("Zac");
                p4.setSurname("Brown");
                p4.setWeight(171);
                p4.setHeight(79);
                p4.setAge(28);

                Player p5 = new Player();
                p5.setId(5L);
                p5.setName("Daniel");
                p5.setSurname("Green");
                p5.setWeight(188);
                p5.setHeight(65);
                p5.setAge(22);

                Player p6 = new Player();
                p6.setId(6L);
                p6.setName("Gery");
                p6.setSurname("Batman");
                p6.setWeight(176);
                p6.setHeight(83);
                p6.setAge(32);

                Player p7 = new Player();
                p7.setId(7L);
                p7.setName("Rymond");
                p7.setSurname("Crosby");
                p7.setWeight(167);
                p7.setHeight(74);
                p7.setAge(25);

                Player p8 = new Player();
                p8.setId(8L);
                p8.setName("Sidney");
                p8.setSurname("DeVito");
                p8.setWeight(177);
                p8.setHeight(81);
                p8.setAge(17);

                Player p9 = new Player();
                p9.setId(9L);
                p9.setName("Roberto");
                p9.setSurname("Luongo");
                p9.setWeight(182);
                p9.setHeight(91);
                p9.setAge(30);

		// MATCHES - home team vs away team
		Match m1vs2 = new Match(); // result 1:0, round 1, first
		m1vs2.setId(1L);
		m1vs2.setHomeTeam(t1);
		m1vs2.setAwayTeam(t2);
		m1vs2.setRound(1);
		m1vs2.setMatchDate(new Date(1));

		Match m1vs3 = new Match(); // 1:1, round 1, second
		m1vs3.setId(2L);
		m1vs3.setHomeTeam(t1);
		m1vs3.setAwayTeam(t3);
		m1vs3.setRound(1);
		m1vs3.setMatchDate(new Date(2));

		Match m2vs1 = new Match(); // 0:1, round 2, first
		m2vs1.setId(3L);
		m2vs1.setHomeTeam(t2);
		m2vs1.setAwayTeam(t1);
		m2vs1.setRound(2);
		m2vs1.setMatchDate(new Date(4));

		Match m2vs3 = new Match(); // 1:0, round 1, third match
		m2vs3.setId(4L);
		m2vs3.setHomeTeam(t2);
		m2vs3.setAwayTeam(t3);
		m2vs3.setRound(1);
		m2vs3.setMatchDate(new Date(3));

		Match m3vs1 = new Match(); // 1:0, round 2, second
		m3vs1.setId(5L);
		m3vs1.setHomeTeam(t3);
		m3vs1.setAwayTeam(t1);
		m3vs1.setRound(2);
		m3vs1.setMatchDate(new Date(5));

		Match m3vs2 = new Match(); // 0:0, round 2, third
		m3vs2.setId(6L);
		m3vs2.setHomeTeam(t3);
		m3vs2.setAwayTeam(t2);
		m3vs2.setRound(2);
		m3vs2.setMatchDate(new Date(6));

		// GOALS - 6 goals

		Goal g1 = new Goal();
		g1.setId(1L);
		g1.setMatch(m1vs2); // TODO add player and time according to results
		g1.setScorePlayer(p1);
                g1.setAssistPlayer(p4);

		Goal g2 = new Goal();
		g2.setId(2L);
		g2.setMatch(m1vs3);
		g2.setScorePlayer(p3);
                g2.setAssistPlayer(p4);

		Goal g3 = new Goal();
		g3.setId(3L);
		g3.setMatch(m1vs3);
		g3.setScorePlayer(p1);
                g3.setAssistPlayer(p5);
                
		Goal g4 = new Goal();
		g4.setId(4L);
		g4.setMatch(m2vs1);
		g4.setScorePlayer(p1);
                g4.setAssistPlayer(p5);
                
		Goal g5 = new Goal();
		g5.setId(5L);
		g5.setMatch(m2vs3);
		g5.setScorePlayer(p2);
                g5.setAssistPlayer(p6);
                
		Goal g6 = new Goal();
		g6.setId(6L);
		g6.setMatch(m3vs1);
		g6.setScorePlayer(p3);
                g6.setAssistPlayer(p7);
                
		// MAPPED BY ATTRIBUTES

		t1.setHomeMatches(list(new Match[]{ m1vs2, m1vs3 }));
		t1.setAwayMatches(list(new Match[]{ m2vs1, m3vs1 }));

		t2.setHomeMatches(list(new Match[]{ m2vs1, m2vs3 }));
		t2.setAwayMatches(list(new Match[]{ m1vs2, m3vs2 }));

		t3.setHomeMatches(list(new Match[]{ m3vs1, m3vs2 }));
		t3.setAwayMatches(list(new Match[]{ m1vs3, m2vs3 }));

		m1vs2.setGoals(list(new Goal[]{ g1 }));
		m1vs3.setGoals(list(new Goal[]{ g2, g3 }));
		m2vs1.setGoals(list(new Goal[]{ g4 }));
		m2vs3.setGoals(list(new Goal[]{ g5 }));
		m3vs1.setGoals(list(new Goal[]{ g6 }));
		m3vs2.setGoals(list(new Goal[]{  }));

		// SETUP THE MOCK DAOs
		doReturn( t1 ).when(teamDAOMock).getById(1L);
		doReturn( t2 ).when(teamDAOMock).getById(2L);
		doReturn( t3 ).when(teamDAOMock).getById(3L);
		doReturn(list(new Team[]{ t1, t2, t3 })).when(teamDAOMock).findAll();
		doReturn( t1 ).when(teamDAOMock).findTeamByPlayer(p1);
		doReturn( t2 ).when(teamDAOMock).findTeamByPlayer(p2);
		doReturn( t3 ).when(teamDAOMock).findTeamByPlayer(p3);


		doReturn( m1vs2 ).when(matchDAOMock).getById(1L);
		doReturn( m1vs3 ).when(matchDAOMock).getById(2L);
		doReturn( m2vs1 ).when(matchDAOMock).getById(3L);
		doReturn( m2vs3 ).when(matchDAOMock).getById(4L);
		doReturn( m3vs1 ).when(matchDAOMock).getById(5L);
		doReturn( m3vs2 ).when(matchDAOMock).getById(6L);
		doReturn(list(
				new Match[]{ m1vs2, m1vs3, m2vs1, m2vs3, m3vs1, m3vs2 }
		)).when(matchDAOMock).findAll();

		doReturn(list(new Match[]{ m1vs2, m1vs3 })).when(matchDAOMock).findByHomeTeam(t1);
		doReturn(list(new Match[]{ m2vs1, m2vs3 })).when(matchDAOMock).findByHomeTeam(t2);
		doReturn(list(new Match[]{ m3vs1, m3vs2 })).when(matchDAOMock).findByHomeTeam(t3);
		doReturn(list(new Match[]{ m2vs1, m3vs1 })).when(matchDAOMock).findByAwayTeam(t1);
		doReturn(list(new Match[]{ m1vs2, m3vs2 })).when(matchDAOMock).findByAwayTeam(t2);
		doReturn(list(new Match[]{ m1vs3, m2vs3 })).when(matchDAOMock).findByAwayTeam(t3);
                
                doReturn(list(new Match[]{m1vs2, m1vs3, m2vs1, m2vs3, m3vs1, m3vs2})).when(matchDAOMock).findAll();
                
                
                
                doReturn( g1 ).when(goalDAOMock).getById(1L);
		doReturn( g2 ).when(goalDAOMock).getById(2L);
		doReturn( g3 ).when(goalDAOMock).getById(3L);
                doReturn( g4 ).when(goalDAOMock).getById(4L);
		doReturn( g5 ).when(goalDAOMock).getById(5L);
		doReturn( g6 ).when(goalDAOMock).getById(6L);
                
                doReturn( list(new Goal[]{g1, g3, g4}) ).when(goalDAOMock).findByScorePlayer(p1);
                doReturn( list(new Goal[]{g5}) ).when(goalDAOMock).findByScorePlayer(p2);
                doReturn( list(new Goal[]{g2, g6}) ).when(goalDAOMock).findByScorePlayer(p3);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByScorePlayer(p4);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByScorePlayer(p5);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByScorePlayer(p6);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByScorePlayer(p7);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByScorePlayer(p8);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByScorePlayer(p9);
                
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByAssistPlayer(p1);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByAssistPlayer(p2);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByAssistPlayer(p3);
                doReturn( list(new Goal[]{g1, g2}) ).when(goalDAOMock).findByAssistPlayer(p4);
                doReturn( list(new Goal[]{g3, g4}) ).when(goalDAOMock).findByAssistPlayer(p5);
                doReturn( list(new Goal[]{g5}) ).when(goalDAOMock).findByAssistPlayer(p6);
                doReturn( list(new Goal[]{g6}) ).when(goalDAOMock).findByAssistPlayer(p7);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByAssistPlayer(p8);
                doReturn( list(new Goal[]{}) ).when(goalDAOMock).findByAssistPlayer(p9);
                
                doReturn(list(new Player[]{p1,p2,p3,p4,p5,p6,p7,p8,p9})).when(playerDAOMock).findAll();
	}



	public TeamDAO getTeamDAOMock() {
		return teamDAOMock;
	}



	public MatchDAO getMatchDAOMock() {
		return matchDAOMock;
	}
        
        public GoalDAO getGoalDAOMock() {
                return goalDAOMock;
        }
        
        public PlayerDAO getPlayerDAOMock() {
                return playerDAOMock;
        }



	// create a list of E from an array of E
	private <E> List<E> list(E[] array)
	{
		List<E> list = new ArrayList<E>();

		for(E e: array)
		{
			list.add(e);
		}

		return list;
	}
}