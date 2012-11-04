package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.MatchDTO;
import cz.muni.fi.pa165.fast.dto.PlayerDTO;

public interface PlayerService
{
        /**
         * Creating player.
         * 
         * @param dto Player data transfer object
         * @return player id
         */
	public long create(PlayerDTO dto);
	
        /**
         * Updating player.
         * 
         * @param dto Player data transfer object
         * @return player id
         */
	public long update(PlayerDTO dto);
	
        /**
         * Deleting player.
         * 
         * @param dto Player data transfer object
         */
	public void delete(PlayerDTO dto);
	
        /**
         * Creating sorted list of all Players.
         * 
         * @param orderBy Type of sorting method
         * @return sorted list of player data transfer object
         */
	public List<PlayerDTO> findAll(PlayerOrderBy orderBy);
}
