package com.iheart.ms.exceptions;

import org.apache.ibatis.exceptions.PersistenceException;

public class EntityExistsException extends PersistenceException {
	
	  /**
     * Constructs a new <code>EntityExistsException</code> exception with
     * <code>null</code> as its detail message.
     */
    public EntityExistsException() {
        super();
    }

    /**
     * Constructs a new <code>EntityExistsException</code> exception with the
     * specified detail message.
     *
     * @param message
     *            the detail message.
     */
    public EntityExistsException(String message) {
        super(message);
    }


}
