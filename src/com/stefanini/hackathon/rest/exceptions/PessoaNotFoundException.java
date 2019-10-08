package com.stefanini.hackathon.rest.exceptions;

public class PessoaNotFoundException extends Exception {
	public PessoaNotFoundException(final String mensagem) {
		super(mensagem);
	}

	public PessoaNotFoundException(final Throwable t) {
		super(t);
	}

	public PessoaNotFoundException(final String mensagem, final Throwable t) {
		super(mensagem, t);
	}
}
