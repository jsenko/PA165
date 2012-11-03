package cz.muni.fi.pa165.fast.convert;


public interface Convert<E, D>
{
	public D fromEntityToDTO(E entity);
	
	public E fromDTOToEntity(D dto);
}
