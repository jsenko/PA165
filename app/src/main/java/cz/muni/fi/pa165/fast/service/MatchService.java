package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.MatchDTO;

/**
 * Service layer class designed to provide prepared match data
 * for the presentation layer.
 * 
 * @author Jakub Senko
 *
 */
public interface MatchService
{
	public void create(MatchDTO dto);
	
	public void update(MatchDTO dto);
	
	public void delete(MatchDTO dto);
	
	/**
	 * 
	 * @return list of MatchDTO entities ordered by date of the game
	 */
	public List<MatchDTO> findAll();
	
	/**
	 * 
	 * @return list of MatchDTO entities ordered by date of the game
	 */
	public List<MatchDTO> findByRound(int round);
	
	/**
	 * 
	 * @return list of MatchDTO entities ordered by date of the game
	 */
	public List<MatchDTO> findByTeam(long teamId);
}
