package cz.muni.fi.pa165.fast.dao;

import java.util.List;

import cz.muni.fi.pa165.fast.model.User;

/**
 * @author Jakub Senko
 */
public interface UserDAO extends DAO<User>
{
    public User findByLogin(String login);
    
    public List<User> findAll();
}
