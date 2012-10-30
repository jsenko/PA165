package cz.muni.fi.pa165.fast.dao;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;

/**
 * Generic CRUD DAO interface
 * 
 * @author Jakub Senko
 * @version 1.0
 * 
 * @param <T> type of the entity
 */
public interface DAO<T>
{
    /**
     * Creates new entity T in the database.
     * 
     * @param entity T to be added to the database. Cannot be null.
     * @throws IllegalArgumentException if argument is null
     */
    public void create(T entity);
    
    /**
     * Updates an entity T in the database.
     * 
     * @param entity T to be added to the database. Cannot be null.
     * @throws IllegalArgumentException if the argument is null or the entity does not exist.
     */
    public void update(T entity);
    
    /**
     * Delete T from the database.
     * 
     * @param entity T to be deleted. Cannot be null.
     * @throws IllegalArgumentException if the argument is null.
     */
    public void delete(T entity);
    
    /**
     * Retrieve T with the given id from the database.
     * 
     * @param id Id of the entity T. Cannot be null.
     * @return entity T with the specified id or null when it does not exist.
     * @throws IllegalArgumentException if the argument is null.
     */
    public T getById(Long id);
    
    /**
     * Method finds all entities T in database. 
     * 
     * @return Collection of all entities T in database or empty collection when no T are in the database.
     */
    public Collection<T> findAll();
}