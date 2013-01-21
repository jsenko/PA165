package cz.muni.fi.pa165.fast.convert.impl;

import cz.muni.fi.pa165.fast.convert.UserConvert;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.model.User;
import javax.ejb.Local;
import javax.ejb.Stateless;


@Local(value = UserConvert.class)
@Stateless
public class UserConvertImpl implements UserConvert{

    @Override
    public UserDTO fromEntityToDTO(User entity) {
        if (entity == null) {
            return null;
        }
        
        UserDTO dto = new UserDTO();
        if (entity.getId() == null) {
            dto.setId(0);
        } else {
            dto.setId(entity.getId());
        }
        
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        
        return dto;
    }

    @Override
    public User fromDTOToEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        if (dto.getId() == 0) {
            entity.setId(null);
        } else {
            entity.setId(dto.getId());
        }
        
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        
        return entity;
    }
    
}
