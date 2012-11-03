package cz.muni.fi.pa165.fast.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cz.muni.fi.pa165.fast.convert.MatchConvert;
import cz.muni.fi.pa165.fast.dao.MatchDAO;
import cz.muni.fi.pa165.fast.dao.TeamDAO;
import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.Team;
import cz.muni.fi.pa165.fast.service.MatchService;

@Stateless
public class MatchServiceImpl implements MatchService
{

	@EJB
	MatchDAO matchDAO;
	
	@EJB
	MatchConvert convert;
	
	@Override
	public long create(MatchDTO dto)
	{
		Match m = convert.fromDTOToEntity(dto);
		
		matchDAO.create(m);
		
		return m.getId();
	}

	@Override
	public void update(MatchDTO dto)
	{
		Match m = convert.fromDTOToEntity(dto);
		
		matchDAO.update(m);
	}

	@Override
	public void delete(MatchDTO dto)
	{
		// to reduce unnecessary overhead we will not use converter
		Match m = new Match();
		m.setId(dto.getId());
		
		matchDAO.delete(m);
	}

	@Override
	public List<MatchDTO> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatchDTO> findByRound(int round) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MatchDTO> findByTeam(long teamId) {
		// TODO Auto-generated method stub
		return null;
	}

}
