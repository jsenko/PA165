package cz.muni.fi.pa165.fast.security.impl;

import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.security.Acl;
import cz.muni.fi.pa165.fast.security.Authenticator;
import cz.muni.fi.pa165.fast.security.Role;
import cz.muni.fi.pa165.fast.security.SecurityFacade;
import cz.muni.fi.pa165.fast.service.UserService;
import java.lang.reflect.Method;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * High-level security & user login manager
 * 
 * @author Jakub Senko
 */
@Stateless
public class SecurityFacadeImpl implements SecurityFacade
{    
    @EJB
    UserStorage storage;
    
    @EJB
    UserService userService;
    
    @EJB
    private Authenticator authenticator;
    
    
    @Override
    public void setUser(UserDTO user)
    {
        storage.setUser(user);
    }
    
    @Override
    public UserDTO getUser()
    {
        return storage.getUser();
    }
    
    @Override
    public void login(String login, String password)
    {
        // create defaut root admin
        // TODO remove in production!
        if(userService.getByLogin("admin") == null)
        {
            UserDTO user = new UserDTO();
            user.setLogin("admin");
            user.setPassword(DigestUtils.sha256Hex("password"));
            userService.create(user);
        }
        
        UserDTO user = authenticator.authenticate(login, password);
        if(user == null)
        {
            throw new
            IllegalArgumentException("Invalid user login ("+login
                    +") or password.");
        }
        if(storage.getUser() != null)
        {
            throw new IllegalStateException("Some user is already logged in.");
        }
        storage.setUser(user);   
    }

    
    @Override
    public void logout()
    {
        
        storage.setUser(null);    
    }

    @Override
    public UserDTO getCurrentLoggedInUser()
    {
        return storage
                .getUser();
                
    }

    @Override
    public boolean authorize(Method method)
    {
        // get Acl annotations
        Acl acl = method.getAnnotation(Acl.class);
        Role role = null;
        if(acl == null)
        {
            role = Role.NONE;
        }
        else
        {
            role = acl.value();
        }
        // instead of checking which role the user has (userRole),
        // we'll assume that if he is logged in then he is an admin else none
        Role userRole = Role.NONE;
        if(this.getCurrentLoggedInUser() != null)
        {
            userRole = Role.ADMIN;
        }
        
        if(role == userRole || role.hasParent(userRole))
        {
            return true;
        }
        return false;
    }
}
