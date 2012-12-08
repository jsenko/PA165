package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import java.util.List;

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
	
        public PlayerDTO getById(Long id);
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
        
        public List<PlayerDTO> findPlayersByTeam(Long teamId, PlayerOrderBy orderBy);
}
