package service;

import java.util.List;

import domain.Key;

public interface KeyService {
	public void addKey(Key key);
	public void removeContact(int id);
	public List<Key> getAll();
	public Key findById(int id);
	public void save(List<Key> keys);
}
