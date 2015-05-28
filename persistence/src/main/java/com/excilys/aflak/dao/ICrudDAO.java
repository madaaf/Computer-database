package com.excilys.aflak.dao;

public interface ICrudDAO<T> {
	public enum Sort {
		ASC, DESC
	}

	/**
	 * CREATE
	 * 
	 * @param object
	 * @return id object created
	 */
	default public void create(T obj) {
		throw new IllegalStateException("error implementation create");
	}

	/**
	 * READ - find object
	 * 
	 * @param id
	 * @return object find
	 */
	default public T find(Long id) {
		throw new IllegalStateException("error implementation find");
	}

	/**
	 * UPDATE - update object
	 * 
	 * @param obj
	 * @return object updated
	 */
	default public void update(T obj) {
		throw new IllegalStateException("error implementation update");
	}

	/**
	 * DELETE - delete object
	 * 
	 * @param id
	 * @return boolean
	 */
	default public void delete(Long id) {
		throw new IllegalStateException("error implementation delete");
	}

}
