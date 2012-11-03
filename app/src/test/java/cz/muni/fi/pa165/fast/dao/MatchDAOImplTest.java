package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.dao.impl.MatchDAOImpl;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michal Kimle
 */
public class MatchDAOImplTest {
    
    private MatchDAOImpl mdaoi;
    private EntityManagerFactory emf;
    private EntityManager em; 
    
    public MatchDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        
        
        mdaoi = new MatchDAOImpl();
        // perform dependency injection
        Field f = mdaoi.getClass().getDeclaredField("em");
        f.setAccessible(true);
        f.set(mdaoi, em);
    }
    
    @After
    public void tearDown() {
    	em.close();
        emf.close();
    }
    
    @Test
    public void createMatch(){
        Match match = new Match();
        mdaoi.create(match);
        assertNotNull(match.getId());
    }
    
    @Test
    public void createMatchInvalid(){
        Match match = null;
        try {
            mdaoi.create(match);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }
    
    @Test
    public void updateMatch(){
        Match match = new Match();

        em.getTransaction().begin();
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        long time = System.currentTimeMillis()-5000;
        match.setMatchDate(new Date(time));
        
        mdaoi.update(match);
        
        Match DBMatch = em.find(Match.class, matchId);
        
        assertEquals(time, DBMatch.getMatchDate().getTime());
    }
    
    @Test
    public void updateMatchInvalid(){
        Match match = null;
        try {
            mdaoi.update(match);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        match = new Match();
        match.setId(Long.MAX_VALUE);
        try {
            mdaoi.update(match);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }
    
    @Test
    public void deleteMatch(){
        Match match = new Match();

        em.getTransaction().begin();
        em.persist(match);
        em.getTransaction().commit();
        em.clear();
        
        Long matchId = match.getId();
        
        mdaoi.delete(match);
        
        assertNull(em.find(Match.class, matchId));
    }
    
    @Test
    public void deleteMatchInvalid(){
        Match match = null;
        try {
            mdaoi.delete(match);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        match = new Match();
        match.setId(Long.MAX_VALUE);
        try {
            mdaoi.delete(match);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }
    
    @Test
    public void getById(){
        Match match = new Match();
        long time = System.currentTimeMillis();
        match.setMatchDate(new Date(time));

        em.getTransaction().begin();
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        Match DBMatch = mdaoi.getById(matchId);
        
        assertEquals(time, DBMatch.getMatchDate().getTime());
    }
    
    @Test
    public void getByIdInvalid(){
        try {
            mdaoi.getById(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        if(mdaoi.getById(Long.MAX_VALUE) != null) {
            fail();
        }
    }
    
    @Test
    public void findAll(){
        Match match1 = new Match();
        long time = System.currentTimeMillis();
        match1.setMatchDate(new Date(time));
        Match match2 = new Match();
        match2.setMatchDate(new Date(time+1000));

        em.getTransaction().begin();
        em.persist(match1);
        em.persist(match2);
        em.getTransaction().commit();
        
        Long match1Id = match1.getId();
        Long match2Id = match2.getId();
        
        List<Match> matches = mdaoi.findAll();
        
        assertEquals(2, matches.size());
        
        for(Match match:matches){
            if(match1Id.equals(match.getId())) {
                assertEquals(time, match.getMatchDate().getTime());
            }
            
            if(match2Id.equals(match.getId())) {
                assertEquals(time+1000, match.getMatchDate().getTime());
            }
        }
    }
    
    @Test
    public void findByHomeTeam(){
        Team team = new Team();
        Match match1 = new Match();
        match1.setHomeTeam(team);
        Match match2 = new Match();
        match2.setHomeTeam(team);
        Match match3 = new Match();
        match3.setAwayTeam(team);
        team.setAwayMatches(new ArrayList<Match>());
        team.getAwayMatches().add(match3);
        team.setHomeMatches(new ArrayList<Match>());
        team.getHomeMatches().add(match1);
        team.getHomeMatches().add(match2);
        
        long time = System.currentTimeMillis();
        match1.setMatchDate(new Date(time));
        match2.setMatchDate(new Date(time+1000));

        em.getTransaction().begin();
        em.persist(team);
        em.persist(match1);
        em.persist(match2);
        em.persist(match3);
        em.getTransaction().commit();
        
        Long match1Id = match1.getId();
        Long match2Id = match2.getId();
        
        List<Match> matches = mdaoi.findByHomeTeam(team);
        
        assertEquals(2, matches.size());
        
        for(Match match:matches){
            if(match1Id.equals(match.getId())) {
                assertEquals(time, match.getMatchDate().getTime());
            }
            
            if(match2Id.equals(match.getId())) {
                assertEquals(time+1000, match.getMatchDate().getTime());
            }
        }
    }
    
    @Test
    public void findByAwayTeam(){
        Team team = new Team();
        Match match1 = new Match();
        match1.setAwayTeam(team);
        Match match2 = new Match();
        match2.setAwayTeam(team);
        Match match3 = new Match();
        match3.setHomeTeam(team);
        team.setHomeMatches(new ArrayList<Match>());
        team.getHomeMatches().add(match3);
        team.setAwayMatches(new ArrayList<Match>());
        team.getAwayMatches().add(match1);
        team.getAwayMatches().add(match2);
        
        long time = System.currentTimeMillis();
        match1.setMatchDate(new Date(time));
        match2.setMatchDate(new Date(time+1000));

        em.getTransaction().begin();
        em.persist(team);
        em.persist(match1);
        em.persist(match2);
        em.persist(match3);
        em.getTransaction().commit();
        
        Long match1Id = match1.getId();
        Long match2Id = match2.getId();
        
        List<Match> matches = mdaoi.findByAwayTeam(team);
        
        assertEquals(2, matches.size());
        
        for(Match match:matches){
            if(match1Id.equals(match.getId())) {
                assertEquals(time, match.getMatchDate().getTime());
            }
            
            if(match2Id.equals(match.getId())) {
                assertEquals(time+1000, match.getMatchDate().getTime());
            }
        }
    }
    
    @Test
    public void findByRound()
    {
    	// TODO
    	assertTrue(true);
    }
}
