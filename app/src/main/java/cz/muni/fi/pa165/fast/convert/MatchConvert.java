package cz.muni.fi.pa165.fast.convert;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import cz.muni.fi.pa165.fast.dao.GoalDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Goal;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
/**
 * Implementation of the Convert interface for match service.
 * 
 * @author Jakub Senko
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MatchConvert implements Convert<Match, MatchDTO>
{
	@EJB
	TeamDAO teamDAO;

	@Override
	public MatchDTO fromEntityToDTO(Match entity)
	{
		if(entity == null)
		{
			throw new IllegalArgumentException("Match entity is null.");
		}
		if(entity.getHomeTeam() == null || entity.getAwayTeam() == null )
		{
			throw new RuntimeException("Home/Away Team cannot be null");
		}
		
		MatchDTO dto = new MatchDTO();
		dto.setId(entity.getId());
		dto.setRound(entity.getRound());
		dto.setDate(entity.getMatchDate());
		
		dto.setHomeTeamId(entity.getHomeTeam().getId());
		dto.setHomeTeamName(entity.getHomeTeam().getName());
		
		dto.setAwayTeamId(entity.getAwayTeam().getId());
		dto.setAwayTeamName(entity.getAwayTeam().getName());
		
		int homeGoals = 0;
		int awayGoals = 0;
		
		for(Goal g: entity.getGoals())
		{System.err.println(g + "\n" + entity.getHomeTeam());
			if(entity.getHomeTeam().equals(
					teamDAO.findTeamByPlayer(g.getScorePlayer())
			))
			{

				homeGoals++;
			}
			else
			{
				awayGoals++;
			}
		}
		
		dto.setHomeTeamGoals(homeGoals);
		dto.setAwayTeamGoals(awayGoals);
		
		return dto;
	}

	@Override
	public Match fromDTOToEntity(MatchDTO dto)
	{
		if(dto == null)
		{
			throw new IllegalArgumentException("MatchDTO is null.");
		}
		
		// get home team by id
		Team homeTeam = null;
		try
		{
			homeTeam = teamDAO.getById(dto.getHomeTeamId());
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Could not find team with an id = "
					+ dto.getHomeTeamId() , e);
		}
		
		// get away team by id
		Team awayTeam = null;
		try
		{
			awayTeam = teamDAO.getById(dto.getAwayTeamId());
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Could not find team with an id = "
					+ dto.getAwayTeamId() , e);
		}
		
		// create the match
		Match m = new Match();
		m.setId(dto.getId()); // just for tests
		m.setHomeTeam(homeTeam);
		m.setAwayTeam(awayTeam);
		// goals is mappedBy attribute, we will ignore it
		m.setMatchDate(dto.getDate());
		m.setRound(dto.getRound());
		return m;
	}

	
}
