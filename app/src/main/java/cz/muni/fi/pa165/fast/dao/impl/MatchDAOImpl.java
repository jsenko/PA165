package cz.muni.fi.pa165.fast.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;

/**
 * Implementation of the MatchDAO interface.
 * 
 * @author Jakub Senko
 * @version 1.0
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MatchDAOImpl implements MatchDAO
{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void create(Match match)
	{		
		if(match == null)
		{
			throw new IllegalArgumentException("Match is null.");
		}

		em.persist(match);
	}

	@Override
	public void update(Match match)
	{
		if(match == null)
		{
			throw new IllegalArgumentException("Match is null.");
		}

		if(em.find(Match.class, match.getId()) == null)
		{
			throw new IllegalArgumentException("Match does not exist.");
		}
		
		em.merge(match);
	}

	@Override
	public void delete(Match match)
	{
		if(match == null)
		{
			throw new IllegalArgumentException("Match is null.");
		}

		if(em.find(Match.class, match.getId()) == null)
		{
			throw new IllegalArgumentException("Match does not exist.");
		}

		Match managed = em.merge(match);
		
		em.remove(managed);		
	}

	@Override
	public Match getById(Long id)
	{
		if(id == null)
		{
			throw new IllegalArgumentException("Id is null.");
		}
		
		return em.find(Match.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> findAll()
	{
		return em.createQuery("select m from Match m").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> findByHomeTeam(Team team)
	{
		if(team == null)
		{
			throw new IllegalArgumentException("Team is null.");
		}
		
		return em.createQuery("select m from Match m where m.homeTeam = :team")
			.setParameter("team", team)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Match> findByAwayTeam(Team team)
	{
		if(team == null)
		{
			throw new IllegalArgumentException("Team is null.");
		}

		return em.createQuery("select m from Match m where m.awayTeam = :team")
			.setParameter("team", team)
			.getResultList();
	}
}
