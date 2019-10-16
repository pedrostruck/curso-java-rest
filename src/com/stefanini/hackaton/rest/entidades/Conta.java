package com.stefanini.hackaton.rest.entidades;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries(
	value = { @NamedQuery(
		name = "Conta.findAll",
		query = "SELECT c FROM Conta c"),
	// feita com GenericDAO
//			@NamedQuery(
//				name = "Conta.findById",
//				query = "SELECT c FROM Conta c WHERE c.conta = :id"),
			@NamedQuery(
				name = "Conta.findByAgenciaConta",
				query = "SELECT c from Conta c WHERE c.agencia = :nrAgencia AND c.conta = :nrConta"),
			@NamedQuery(
				name = "Conta.removeById",
				query = "DELETE FROM Conta c WHERE c.id = :id"),
			@NamedQuery(
				name = "Conta.associateContaPessoa",
				query = "DELETE FROM Conta c WHERE c.id = :id") })
@javax.persistence.Entity
@Table(name = "conta")
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String conta;
	private String agencia;
	private String senha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
