package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;

public interface PlayerService
{
	public long create(PlayerDTO dto);
	
	public long update(PlayerDTO dto);
	
	public void delete(PlayerDTO dto);
	
	public List<MatchDTO> findAll(PlayerOrderBy orderBy);
}
