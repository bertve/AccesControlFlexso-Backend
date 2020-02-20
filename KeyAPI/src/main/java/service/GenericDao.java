package service;

import java.util.List;


public interface GenericDao<T> {
	public void add(T item);
	public void remove(T item);
	public List<T> getAll();
	public T findById(long id);
	public void save(List<T> items);
	public void update(T item);
	public boolean exists(Long id) ;
}
