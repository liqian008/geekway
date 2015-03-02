/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.geekway.model.exception;


public class CachedException extends Exception {

	private static final long serialVersionUID = 7303939335406444362L;

	public CachedException() {
		super();
	}

	public CachedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CachedException(String arg0) {
		super(arg0);
	}

	public CachedException(Throwable arg0) {
		super(arg0);
	}

}
