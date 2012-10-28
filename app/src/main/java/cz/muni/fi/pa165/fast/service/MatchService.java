package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.MatchDTO;

public interface MatchService
{
	public long create(MatchDTO dto);
	
	public long update(MatchDTO dto);
	
	public void delete(MatchDTO dto);
	
	public List<MatchDTO> findAll();
	
	public List<MatchDTO> findByRound(int round);
	
	public List<MatchDTO> findByTeam(long teamId);
}
