package cz.muni.fi.pa165.fast.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.fast.dao.UserDAO;
import cz.muni.fi.pa165.fast.model.User;

@Stateless
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(name = "TestPU")
    private EntityManager em;

    @Override
    public void create(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("User is null.");
        }
        if(findByLogin(entity.getLogin()) != null)
        {
            throw new IllegalStateException("User login already exist.");
        }
        em.persist(entity);
    }

    @Override
    public void update(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("User is null.");
        }

        if (em.find(User.class, entity.getId()) == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(User entity) {
        if (entity == null) {
            throw new IllegalArgumentException("User is null.");
        }

        if (em.find(User.class, entity.getId()) == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        em.remove(em.merge(entity));
    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }

        User manUser = em.find(User.class, id);
        if (manUser == null) {
            throw new IllegalArgumentException("user not in DB");
        }

        return manUser;
    }

    @Override
    public User findByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        User user = null;

        try {
            user = em.createQuery("select u from User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (Exception e) {
            // access denied
        } finally {
            return user;
        }

    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
