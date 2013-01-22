package cz.muni.fi.pa165.fast.dao;

import java.util.List;

import cz.muni.fi.pa165.fast.model.User;

/**
 * Interface of DAO object of entity User
 * 
 * @author Jakub Senko
 */
public interface UserDAO extends DAO<User>
{
    /**
     * Finds User by login
     * 
     * @param login login name
     * @return User with assoctiated login
     */
    public User findByLogin(String login);
    
}
