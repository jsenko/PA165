package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Team;
import javax.ejb.Stateless;

@Stateless
public interface TeamConvert extends Convert<Team, TeamDTO> {

    @Override
    public TeamDTO fromEntityToDTO(Team entity);

    @Override
    public Team fromDTOToEntity(TeamDTO dto);
}
