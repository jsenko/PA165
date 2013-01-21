package cz.muni.fi.pa165.fast.convert;

import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.model.User;

public interface UserConvert extends Convert<User, UserDTO>{
    
    @Override
    public UserDTO fromEntityToDTO(User entity);

    @Override
    public User fromDTOToEntity(UserDTO dto);
}
