package cz.muni.fi.pa165.fast.security;

import cz.muni.fi.pa165.fast.model.User;

/**
 * 
 * @author Jakub Senko
 */
public interface Authenticator
{
    /**
     * 
     * @param login
     * @param password
     * @return authenticated user or null on failure
     */
    public User authenticate(String login, String password);
}
