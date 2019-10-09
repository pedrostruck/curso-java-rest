package com.stefanini.hackathon.rest.entidades;

public class Pessoa {
	private String nome;
	private String cpf;
	private Conta conta;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isIncomplete() {
		if (this.nome == null || this.cpf == null) {
			return true;
		}
		return false;
	}

}
