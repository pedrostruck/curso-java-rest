package com.stefanini.hackathon.rest.exceptions;

public class NegocioException extends Exception {

	public NegocioException(final String mensagem) {
		super(mensagem);
	}

	public NegocioException(final Throwable t) {
		super(t);
	}

	public NegocioException(final String mensagem, final Throwable t) {
		super(mensagem, t);
	}

}
