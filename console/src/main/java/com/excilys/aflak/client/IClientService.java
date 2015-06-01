package com.excilys.aflak.client;

import java.util.List;

public interface IClientService<T> {

	List<T> list();

	T find(Long id);

	default void create(T o) {
	};

	default void update(T o) {
	};

	default void delete(long id) {
	};

}
