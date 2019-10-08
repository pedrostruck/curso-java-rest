package com.stefanini.hackathon.rest;

public class Conta {
	private Integer id;
	private String agencia;
	private String numeroDaConta;
	private String senha;

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return numeroDaConta;
	}

	public void setConta(String conta) {
		this.numeroDaConta = conta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
