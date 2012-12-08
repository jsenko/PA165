package cz.muni.fi.pa165.fast.convert;

/**
 *
 * @author Jakub Senko
 *
 * @param <E> type of the entity object.
 * @param <D> type of the DTO object.
 */
public interface Convert<E, D> {

    public D fromEntityToDTO(E entity);
    
    public E fromDTOToEntity(D dto);
}
