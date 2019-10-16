package com.stefanini.hackathon.rest.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@NamedQueries(
	value = {
			@NamedQuery(
				name = "Pessoa.findAll",
				query = "SELECT p FROM Pessoa p"),
			@NamedQuery(
				name = "Pessoa.removeByCpf",
				query = "DELETE FROM Pessoa p WHERE p.cpf = :cpf"),
			@NamedQuery(
				name = "Pessoa.findByCpf",
				query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf") })
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	
	@Transient
	private Conta conta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

}
