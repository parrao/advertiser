package com.iheart.ms.exceptions;

import org.apache.ibatis.exceptions.PersistenceException;

public class NoResultException extends PersistenceException {
	
	/**
	 * Constructs a new <code>NoResultException</code> exception with
	 * <code>null</code> as its detail message.
	 */
	public NoResultException() {
		super();
	}

	/**
	 * Constructs a new <code>NoResultException</code> exception with the
	 * specified detail message.
	 *
	 * @param message
	 *            the detail message.
	 */
	public NoResultException(String message) {
		super(message);
	}

}
