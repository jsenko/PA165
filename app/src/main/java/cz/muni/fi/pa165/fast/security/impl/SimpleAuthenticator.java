package cz.muni.fi.pa165.fast.security.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cz.muni.fi.pa165.fast.dao.UserDAO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.security.Authenticator;

/**
 * 
 * @author Jakub Senko
 */
@Stateless
public class SimpleAuthenticator implements Authenticator
{
    @EJB
    UserDAO userDAO;
    
    @Override
    public User authenticate(String login, String password)
    {
        User user = userDAO.findByLogin(login);
        if(user != null && password.equals(user.getPassword()))
        {
            return user;
        }
        
        return null;
    }

}
