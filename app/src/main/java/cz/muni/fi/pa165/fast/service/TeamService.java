package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TeamService {

    /**
    * Creating team.
    * 
    * @param dto Team data transfer object
    */
    public void create(TeamDTO dto);

    /**
    * Updating team.
    * 
    * @param dto Team data transfer object
    */
    public void update(TeamDTO dto);

    /**
    * Deleting team.
    * 
    * @param dto Team data transfer object
    */
    public void delete(TeamDTO dto);

    public TeamDTO getById(Long id);
    /**
     * Finding all of team data transfer objects
     * 
     * @return list of all team data transfer object
     */
    public List<TeamDTO> findAll();

    /**
     * Finding team by player`s id.
     * 
     * @param playerId id of player
     * @return team data transfer object
     */
    public TeamDTO findByPlayer(long playerId);
}
