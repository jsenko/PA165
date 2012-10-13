package cz.muni.fi.pa165.fast.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.muni.fi.pa165.fast.dao.impl.TeamDAOImpl;
import cz.muni.fi.pa165.fast.model.Team;


public class TeamDAOTest
{

    private static EntityManagerFactory emf;

    private TeamDAO dao;
   
    @BeforeClass
    public static void beforeClass()
    {
        emf = Persistence.createEntityManagerFactory("TestPU");
    }

    @AfterClass
    public static void afterClass()
    {
        emf.close();
    }
   
    @Before
    public void before()
    {
        dao = new TeamDAOImpl();
        dao.setEntityManagerFactory(emf);
    } 
   
    @Test
    public void testTest()
    {
        Team t = new Team();
        t.setName("FC Loosers");
        
        dao.create(t);
    }


}
