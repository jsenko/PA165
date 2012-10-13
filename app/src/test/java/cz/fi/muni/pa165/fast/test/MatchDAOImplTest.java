package cz.fi.muni.pa165.fast.test;

import cz.muni.fi.pa165.fast.dao.impl.MatchDAOImpl;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
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
    
    public MatchDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("FAST-TestPU");
        mdaoi = new MatchDAOImpl();
        mdaoi.setEntityManagerFactory(emf);
    }
    
    @After
    public void tearDown() {
        emf.close();
    }
    
    @Test
    public void createMatch(){
        Match match = new Match();
        mdaoi.create(match);
        assertNotNull(match.getId());
    }
    
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
        
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        long time = System.currentTimeMillis();
        match.setMatchDate(new Date(time));
        
        mdaoi.update(match);
        
        Match DBMatch = em.find(Match.class, matchId);
        
        assertEquals(time, DBMatch.getMatchDate().getTime());
        em.close();
    }
    
    @Test
    public void updateMatchinvalid(){
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
        em.close();
    }
    
    @Test
    public void deleteMatch(){
        Match match = new Match();
        
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        mdaoi.delete(match);
        
        assertNull(em.find(Match.class, matchId));
        em.close();
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
        
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        Match DBMatch = mdaoi.getById(matchId);
        
        assertEquals(time, DBMatch.getMatchDate().getTime());
        em.close();
    }
    
    @Test
    public void getByIdInvalid(){
        try {
            mdaoi.getById(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        
        Match match = new Match();
        match.setId(Long.MAX_VALUE);
        try {
            mdaoi.getById(match);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
    }
    
    @Test
    public void findAll(){
        Match match1 = new Match();
        long time = System.currentTimeMillis();
        match1.setMatchDate(new Date(time));
        Match match2 = new Match();
        match2.setMatchDate(new Date(time+1000));
        
        EntityManager em = emf.createEntityManager();

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
        em.close();
    }
    
    @Test
    public void findByHomeTeam(){
        Team team = new Team();
        Match match = new Match();
        match.setHomeTeam(team);
        
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(team);
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        assertEquals(matchId, mdaoi.findByHomeTeam(team).getId());
        
        em.close();
    }
    
    @Test
    public void findByAwayTeam(){
        Team team = new Team();
        Match match = new Match();
        match.setAwayTeam(team);
        
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(team);
        em.persist(match);
        em.getTransaction().commit();
        
        Long matchId = match.getId();
        
        assertEquals(matchId, mdaoi.findByAwayTeam(team).getId());
        
        em.close();
    }
}
