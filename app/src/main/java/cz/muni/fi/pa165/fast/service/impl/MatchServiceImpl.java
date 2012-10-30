package cz.muni.fi.pa165.fast.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.service.MatchService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MatchServiceImpl implements MatchService
{

	@Override
	public long create(MatchDTO dto)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long update(MatchDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(MatchDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MatchDTO> findAll() {
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
