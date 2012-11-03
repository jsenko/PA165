package cz.muni.fi.pa165.fast.convert;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MatchConvert implements Convert<Match, MatchDTO>
{
	@EJB
	TeamDAO teamDAO;

	@Override
	public MatchDTO fromEntityToDTO(Match entity)
	{
		throw new UnsupportedOperationException("TODO.");
	}

	@Override
	public Match fromDTOToEntity(MatchDTO dto)
	{
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
		m.setHomeTeam(homeTeam);
		m.setAwayTeam(awayTeam);
		// goals is mappedBy attribute, we will ignore it
		m.setMatchDate(dto.getTime());
		m.setRound(dto.getRound());
		return m;
	}

	
}
