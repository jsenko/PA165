package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TeamService {

    public void create(TeamDTO dto);

    public void update(TeamDTO dto);

    public void delete(TeamDTO dto);

    public List<TeamDTO> findAll();

    public TeamDTO findByPlayer(long playerId);
}
