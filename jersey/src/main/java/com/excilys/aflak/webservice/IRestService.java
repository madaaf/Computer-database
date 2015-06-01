package com.excilys.aflak.webservice;

import java.util.List;

public interface IRestService<T> {

	default void create(T obj) {
	};

	/**
	 * Delete a T with the id id.
	 *
	 * @param id
	 */
	void delete(long id);

	/**
	 * Update a T
	 *
	 * @param obj
	 *            the object
	 */
	default void update(T obj) {
	};

	/**
	 * Find an T by his id.
	 *
	 * @param id
	 *            T id
	 * @return the T found or null if doesn't
	 */
	T find(long id);

	/**
	 * Gets the list of all T.
	 *
	 * @return the T list
	 */
	List<T> findAll();
}
