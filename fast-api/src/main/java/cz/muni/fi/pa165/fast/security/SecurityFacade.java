package cz.muni.fi.pa165.fast.security;

import java.lang.reflect.Method;

import cz.muni.fi.pa165.fast.model.User;

/**
 * 
 * @author Jakub Senko
 *
 */
public interface SecurityFacade
{
    /**
     * retrieve user credentials to be saved in the upper layer
     * for example in httsession to preserve user login session between
     * requests
     * 
     * @return
     */
    public User getUser();
    
    /**
     * set user which was retrieved from httpsession in the presentation layer
     * @param user
     */
    public void setUser(User user);
    
    /**
     * 
     * @param login
     * @param password
     * @throws IllegalArgumentException when user does not exist or authentication fails
     * @throws IllegalStateException when another user is already logged in
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
    public User getCurrentLoggedInUser();
    
    /**
     * can current user invoke given method?
     * 
     * @param method
     * @return
     */
    public boolean authorize(Method method);
}
