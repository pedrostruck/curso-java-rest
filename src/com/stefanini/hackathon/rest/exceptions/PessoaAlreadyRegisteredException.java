package com.stefanini.hackathon.rest.exceptions;

public class PessoaAlreadyRegisteredException extends Exception {
	public PessoaAlreadyRegisteredException(final String mensagem) {
		super(mensagem);
	}

	public PessoaAlreadyRegisteredException(final Throwable t) {
		super(t);
	}

	public PessoaAlreadyRegisteredException(final String mensagem,
			final Throwable t) {
		super(mensagem, t);
	}
}
