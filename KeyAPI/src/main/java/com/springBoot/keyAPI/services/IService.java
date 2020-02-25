package com.springBoot.keyAPI.services;

import java.util.List;

public interface IService<T> {
	public List<T> getAll();
	public boolean add(T item);
	public boolean remove(long id);
	public T getById(long id);
	public boolean update(T item);
	public boolean removeAll();
}
