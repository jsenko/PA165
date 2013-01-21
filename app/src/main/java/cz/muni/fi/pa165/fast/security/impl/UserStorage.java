package cz.muni.fi.pa165.fast.security.impl;

import cz.muni.fi.pa165.fast.dto.UserDTO;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
public class UserStorage
{
    private final ThreadLocal<UserDTO> storage = new ThreadLocal<UserDTO>();

    public UserDTO getUser() {
        return storage.get();
    }
    
    public void setUser(UserDTO user)
    {
        this.storage.set(user);
    }
}
