package cz.muni.fi.pa165.fast.dao;


import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michal Kimle
 */
public class MatchDAOImplTest {
    
    private static Context context;
    private static MatchDAO mdaoi;
    private static MatchFakeEntityManager em; 

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        context = EJBContainer.createEJBContainer().getContext();
        mdaoi = (MatchDAO) context.lookup("java:global/app/MatchDAOImpl");
        em = (MatchFakeEntityManager) context.lookup("java:global/app/MatchFakeEntityManager");
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException
    {
        context.close();
    }
    
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createMatch(){
        Match match = new Match();
        
        mdaoi.create(match);
        
        assertNotNull(match.getId());
        
        em.remove(match);
    }
    
    @Test
    public void createMatchInvalid(){
        Match match = null;
        try {
            mdaoi.create(match);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }
    
    @Test
    public void updateMatch(){
        Match match = new Match();

        em.persist(match);
        
        Long matchId = match.getId();
        
        long time = System.currentTimeMillis()-5000;
        match.setMatchDate(new Date(time));
        
        mdaoi.update(match);
        
        Match DBMatch = em.find(Match.class, matchId);
        
        assertEquals(time, DBMatch.getMatchDate().getTime());
        
        em.remove(match);
    }
    
    @Test
    public void updateMatchInvalid(){
        Match match = null;
        try {
            mdaoi.update(match);
            fail();
        } catch (EJBException ex) {
            //OK
        }
        
        match = new Match();
        match.setId(Long.MAX_VALUE);
        try {
            mdaoi.update(match);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }
    
    @Test
    public void deleteMatch(){
        Match match = new Match();

        em.persist(match);
        
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
        } catch (EJBException ex) {
            //OK
        }
        
        match = new Match();
        match.setId(Long.MAX_VALUE);
        try {
            mdaoi.delete(match);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }
    
    @Test
    public void getById(){
        Match match = new Match();
        long time = System.currentTimeMillis();
        match.setMatchDate(new Date(time));

        em.persist(match);
        
        Long matchId = match.getId();
        
        Match DBMatch = mdaoi.getById(matchId);
        
        assertEquals(time, DBMatch.getMatchDate().getTime());
        
        em.remove(match);
    }
    
    @Test
    public void getByIdInvalid(){
        try {
            mdaoi.getById(null);
            fail();
        } catch (EJBException ex) {
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

        em.persist(match1);
        em.persist(match2);
        
        Long match1Id = match1.getId();
        Long match2Id = match2.getId();
        
        Collection<Match> matches = mdaoi.findAll();
        
        assertEquals(2, matches.size());
        
        for(Match match:matches){
            if(match1Id.equals(match.getId())) {
                assertEquals(time, match.getMatchDate().getTime());
            }
            
            if(match2Id.equals(match.getId())) {
                assertEquals(time+1000, match.getMatchDate().getTime());
            }
        }
        
        em.remove(match1);
        em.remove(match2);
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

        em.persist(team);
        
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
        
        em.remove(match1);
        em.remove(match2);
        em.remove(match3);
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

        em.persist(team);
        
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
        
        em.remove(match1);
        em.remove(match2);
        em.remove(match3);
    }
    
    @Stateless
    public static class MatchFakeEntityManager {

        @PersistenceContext(name = "TestPU")
        private EntityManager em;

        public void remove(Match m) {
            em.remove(em.find(Match.class, m.getId()));
        }

        public void persist(Object o) {
            em.persist(o);
        }

        public Match find(Class<Match> c, Object o) {
            return em.find(c, o);
        }
    }
}
