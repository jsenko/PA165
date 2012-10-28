package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.GoalDTO;

public interface GoalService
{
	public long create(GoalDTO dto);
	
	public long update(GoalDTO dto);
	
	public void delete(GoalDTO dto);
	
	public List<GoalDTO> findByMatch(long matchId);
}
