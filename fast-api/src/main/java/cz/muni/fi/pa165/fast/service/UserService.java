package cz.muni.fi.pa165.fast.service;

import cz.muni.fi.pa165.fast.dto.GoalDTO;
import cz.muni.fi.pa165.fast.dto.UserDTO;

import java.util.List;

/**
 * 
 * @author Jakub Senko
 *
 */
public interface UserService
{

    public long create(UserDTO dto);

    //public long update(UserDTO dto);

    public void delete(UserDTO dto);

    public List<UserDTO> findAll();

    public UserDTO getByLogin(long id);
}
