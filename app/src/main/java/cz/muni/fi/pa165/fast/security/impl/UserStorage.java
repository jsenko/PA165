package cz.muni.fi.pa165.fast.security.impl;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import cz.muni.fi.pa165.fast.model.User;

@Singleton
@Startup
public class UserStorage
{
    private final ThreadLocal<User> storage = new ThreadLocal<User>();

    public User getUser() {
        return storage.get();
    }
    
    public void setUser(User user)
    {
        this.storage.set(user);
    }
}
