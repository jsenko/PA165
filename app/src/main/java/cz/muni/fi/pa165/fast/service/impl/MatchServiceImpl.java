package cz.muni.fi.pa165.fast.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.MatchService;

/**
 * @author Jakub Senko
 */
@Stateless
public class MatchServiceImpl implements MatchService
{

	//@EJB(name="java:global/myapp/MatchDAOImpl!cz.muni.fi.pa165.fast.dao.MatchDAO")
	@EJB
        MatchDAO matchDAO;
	
	//@EJB(name="java:global/myapp/MatchConvertImpl!cz.muni.fi.pa165.fast.convert.MatchConvert")
        @EJB
	MatchConvert convert;
	
	//@EJB(name="java:global/myapp/TeamDAOImpl!cz.muni.fi.pa165.fast.dao.TeamDAO")
        @EJB
	TeamDAO teamDAO;
	
	@Override
	public void create(MatchDTO dto)
	{
		Match m = convert.fromDTOToEntity(dto);
		
		matchDAO.create(m);
	}

	@Override
	public void update(MatchDTO dto)
	{
		try
		{
			Match m = convert.fromDTOToEntity(dto);
			matchDAO.update(m);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Update opration failed", e);
		}
	}

	@Override
	public void delete(MatchDTO dto)
	{		
		try
		{
			// to reduce unnecessary overhead we will not use converter
			Match m = new Match();
			m.setId(dto.getId());
			matchDAO.delete(m);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Delete operation failed", e);
		}
	}

	@Override
	public List<MatchDTO> findAll()
	{
		Collection<Match> matches = matchDAO.findAll();
		List<MatchDTO> dtos = new ArrayList<MatchDTO>();
		for(Match m: matches)
		{
			dtos.add(convert.fromEntityToDTO(m));
		}
		Collections.sort(dtos);
		return dtos;
	}

	@Override
	public List<MatchDTO> findByRound(int round)
	{
		Collection<Match> matches = matchDAO.findAll();
		List<MatchDTO> dtos = new ArrayList<MatchDTO>();
		for(Match m: matches)
		{
			if(m.getRound() == round) dtos.add(convert.fromEntityToDTO(m));
		}
		Collections.sort(dtos);
		return dtos;
	}

	@Override
	public List<MatchDTO> findByTeam(long teamId)
	{
		// get the team by id
		Team t = teamDAO.getById(teamId);
		
		// get matches played as a home team
		List<Match> matches = matchDAO.findByHomeTeam(t);
		
		// get matches played as an away team
		matches.addAll( matchDAO.findByAwayTeam(t) );
		
		List<MatchDTO> dtos = new ArrayList<MatchDTO>();
		//convert
		for(Match m: matches)
		{
			dtos.add(convert.fromEntityToDTO(m));
		}
		Collections.sort(dtos);
		return dtos;
	}

		@Override
		public MatchDTO getById(long id)
		{
			try
			{
	
				return convert.fromEntityToDTO(matchDAO.getById(id));
			}
			catch(Exception e)
			{
				throw new RuntimeException("getById operation failed", e);
			}
		}

}
