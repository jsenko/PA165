package cz.muni.fi.pa165.fast.service;

import java.util.List;

import cz.muni.fi.pa165.fast.dto.UserDTO;

/**
 * 
 * @author Jakub Senko
 *
 */
public interface UserService
{

    public long create(UserDTO dto);

    public void delete(UserDTO dto);

    public List<UserDTO> findAll();

    public UserDTO getByLogin(String login);
}
