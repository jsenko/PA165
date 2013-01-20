package cz.muni.fi.pa165.fast.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.fast.dao.UserDAO;
import cz.muni.fi.pa165.fast.model.User;

@Stateless
public class UserDAOImpl implements UserDAO
{

    @PersistenceContext//(name = "TestPU")
    private EntityManager em;
    
    @Override
    public void create(User entity)
    {
        em.persist(entity);    
    }

    @Override
    public void update(User entity)
    {
        em.merge(entity);   
    }

    @Override
    public void delete(User entity)
    {
        em.remove(em.merge(entity));    
    }

    @Override
    public User getById(Long id)
    {
        return em.find(User.class, id);
    }

    @Override
    public User findByLogin(String login)
    {
        User user = null;
        
        try
        {
            user = em.createQuery("select u from User u where u.login = :login", User.class)
               .setParameter("login", login)
               .getSingleResult();
        }
        catch(Exception e)
        {
            // fuck off
        }
        return user;
    }

    @Override
    public List<User> findAll()
    {
        return em.createQuery("select u from User u", User.class)
               .getResultList();
    }
}
