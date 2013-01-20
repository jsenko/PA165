package cz.muni.fi.pa165.fast.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cz.muni.fi.pa165.fast.dao.UserDAO;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.impl.AuthorizationInterceptor;
import cz.muni.fi.pa165.fast.service.UserService;

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

    @Override
    @Acl(Role.ADMIN)
    public long create(UserDTO dto)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    @Acl(Role.ADMIN)
    public void delete(UserDTO dto)
    {
        throw new UnsupportedOperationException();    
    }

    @Override
    @Acl(Role.ADMIN)
    public List<UserDTO> findAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    @Acl(Role.ADMIN)
    public UserDTO getByLogin(String login)
    {
        throw new UnsupportedOperationException();
    }


}
