package cz.muni.fi.pa165.fast.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;

/**
 * Implementation of the MatchDAO interface.
 * 
 * @author Jakub Senko
 * @version 1.0
 */
public class MatchDAOImpl implements MatchDAO
{
	
    private EntityManagerFactory emf;

    @Override
    public void setEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    private EntityManager getManager()
    {
    	return emf.createEntityManager();
    }

	@Override
	public void create(Match match)
	{
		EntityManager em = getManager();
		try{
			
		
		if(match == null)
		{
			throw new IllegalArgumentException("Match is null.");
		}
		
		em.getTransaction().begin();
		em.persist(match);
		em.getTransaction().commit();
		
		}finally{ em.close(); }
	}

	@Override
	public void update(Match match)
	{
		if(match == null)
		{
			throw new IllegalArgumentException("Match is null.");
		}
		EntityManager em = getManager();
		try{
		

		
		if(em.find(Match.class, match.getId()) == null)
		{
			throw new IllegalArgumentException("Match does not exist.");
		}
		em.getTransaction().begin();
		em.merge(match);
		em.getTransaction().commit();
		
		}finally{ em.close(); }
	}

	@Override
	public void delete(Match match)
	{
		if(match == null)
		{
			throw new IllegalArgumentException("Match is null.");
		}
		EntityManager em = getManager();
		try{
		if(em.find(Match.class, match.getId()) == null)
		{
			throw new IllegalArgumentException("Match does not exist.");
		}
		em.getTransaction().begin();
		Match managed = em.merge(match);
		em.remove(managed);
		em.getTransaction().commit();
	    }finally{ em.close(); }
		
	}

	@Override
	public Match getById(Long id)
	{
		if(id == null)
		{
			throw new IllegalArgumentException("Id is null.");
		}
		Match match = null;
		EntityManager em = getManager();
		try{

		match = em.find(Match.class, id);
		}finally{ em.close(); }

		return match;
	}

	@Override
	public List<Match> findAll()
	{
		
		EntityManager em = getManager();
		List result = Collections.emptyList();
		try{
		
		result = em.createQuery("select m from Match m").getResultList();

	}finally{ em.close(); }

		
		return result;
	}

	@Override
	public List<Match> findByHomeTeam(Team team)
	{
		if(team == null)
		{
			throw new IllegalArgumentException("Team is null.");
		}
		EntityManager em = getManager();
		List result = Collections.emptyList();
		try
		{
		
		result = em.createQuery("select m from Match m where m.homeTeam = :team")
			.setParameter("team", team)
			.getResultList();
		}
		finally
		{
			em.close();
		}
		return result;
	}

	@Override
	public List<Match> findByAwayTeam(Team team)
	{
		if(team == null)
		{
			throw new IllegalArgumentException("Team is null.");
		}
		EntityManager em = getManager();
		List result = Collections.emptyList();
		try
		{
		
		result = em.createQuery("select m from Match m where m.awayTeam = :team")
			.setParameter("team", team)
			.getResultList();
		}finally
		{
			em.close();
		}
		return result;
	}

}
