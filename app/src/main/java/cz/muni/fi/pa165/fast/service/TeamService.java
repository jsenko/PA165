package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.TeamDTO;

public interface TeamService
{
	public long create(TeamDTO dto);
	
	public long update(TeamDTO dto);
	
	public void delete(TeamDTO dto);
	
	public List<TeamDTO> findAll();
	
	public List<TeamDTO> findByPlayer(long layerId);
}
