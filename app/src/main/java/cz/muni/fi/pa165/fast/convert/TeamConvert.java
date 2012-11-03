package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Team;

public class TeamConvert {

    public static TeamDTO fromEntityToDTO(Team entity) {
        if (entity == null) {
            return null;
        }
        TeamDTO dto = new TeamDTO();
        if (entity.getId() == null) {
            dto.setId(0);
        } else {
            dto.setId(entity.getId());
        }
        dto.setName(entity.getName());

        return dto;
    }

    public static Team fromDTOToEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }
        Team entity = new Team();
        if (dto.getId() == 0) {
            entity.setId(null);
        } else {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());

        return entity;
    }
}
