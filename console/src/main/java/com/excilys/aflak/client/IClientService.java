package com.excilys.aflak.client;

import java.util.List;

public interface IClientService<T> {

	List<T> list();

	T find(Long id);

	void create(T o);

	void update(T o);

	void delete(long id);

}
