package cz.muni.fi.pa165.fast.security;

import cz.muni.fi.pa165.fast.model.User;

/**
 * 
 * @author Jakub Senko
 *
 */
public interface SecurityFacade
{
    /**
     * 
     * @param login
     * @param password
     * @throws IllegalArgumentException when user does not exist
     * @throws IllegalStateException when another user is already logged in
     * @throws IllegalArgumentException when authentication fails
     */
    public void login(String login, String password);
    
    /**
     * @throws IllegalStateException when no one is logged in
     */
    public void logout();
    
    /**
     * 
     * @return current user or null when no one is logged in
     */
    public User getCurrentUser();
}
