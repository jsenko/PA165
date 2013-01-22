package cz.muni.fi.pa165.fast.service.impl;

import cz.muni.fi.pa165.fast.convert.UserConvert;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cz.muni.fi.pa165.fast.dao.UserDAO;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.impl.AuthorizationInterceptor;
import cz.muni.fi.pa165.fast.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Jakub Senko
 */
@Stateless
@Interceptors({AuthorizationInterceptor.class})
public class UserServiceImpl implements UserService
{

    @EJB
    private UserDAO userDAO;
    @EJB
    private UserConvert userConvert;

    @Override
    @Acl(Role.ADMIN)
    public long create(UserDTO dto)
    {
        try {
            User user = userConvert.fromDTOToEntity(dto);

            userDAO.create(user);
            
            return user.getId();
        } catch (Exception ex) {
            throw new RuntimeException("Create operation failed.", ex);
        }
    }

    @Override
    @Acl(Role.ADMIN)
    public void delete(UserDTO dto)
    {
        try {
            User user = userConvert.fromDTOToEntity(dto);

            userDAO.delete(user);

        } catch (Exception ex) {
            throw new RuntimeException("Delete operation failed.", ex);
        }    
    }

    @Override
    @Acl(Role.ADMIN)
    public List<UserDTO> findAll()
    {
        try {
            Collection<User> users = userDAO.findAll();
            List<UserDTO> dtos = new ArrayList<UserDTO>();

            for (User entity : users) {
                UserDTO dto = userConvert.fromEntityToDTO(entity);
                dtos.add(dto);
            }

            return dtos;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving users.", ex);
        }
    }

    @Override
    public UserDTO getByLogin(String login)
    {
        try {
            User user = userDAO.findByLogin(login);
            UserDTO dto = userConvert.fromEntityToDTO(user);

            return dto;
        } catch (Exception ex) {
            throw new RuntimeException("Error while retrieving user by its login.", ex);
        }
    }


}
