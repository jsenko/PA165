package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.TeamDTO;
import cz.muni.fi.pa165.fast.model.Team;

public class TeamConvert {
    public static TeamDTO fromEntityToDTO(Team entity){
        TeamDTO dto = new TeamDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        
        return dto;
    }
    
    public static Team fromDTOToEntity(TeamDTO dto){
        Team entity = new Team();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        
        return entity;
    }
}
