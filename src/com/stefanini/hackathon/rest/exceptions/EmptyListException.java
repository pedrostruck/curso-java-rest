package com.stefanini.hackathon.rest.exceptions;

public class EmptyListException extends Exception {
	public EmptyListException(final String mensagem) {
		super(mensagem);
	}

	public EmptyListException(final Throwable t) {
		super(t);
	}

	public EmptyListException(final String mensagem, final Throwable t) {
		super(mensagem, t);
	}
}
