package cz.muni.fi.pa165.fast.security.impl;

import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.security.Authenticator;
import cz.muni.fi.pa165.fast.service.UserService;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * 
 * @author Jakub Senko
 */
@Stateless
public class SimpleAuthenticator implements Authenticator
{
    @EJB
    UserService userService;
    
    @Override
    public UserDTO authenticate(String login, String password)
    {
        UserDTO user = userService.getByLogin(login);
        if(user != null && password.equals(user.getPassword()))
        {
            return user;
        }
        
        return null;
    }

}
