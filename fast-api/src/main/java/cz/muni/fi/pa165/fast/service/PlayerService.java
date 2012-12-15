package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {

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

    public List<PlayerDTO> findPlayersByTeam(Long teamId, PlayerOrderBy orderBy);
}
