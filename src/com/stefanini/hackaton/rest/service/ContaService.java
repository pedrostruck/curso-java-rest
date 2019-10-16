package com.stefanini.hackaton.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.stefanini.hackaton.rest.entidades.Conta;
import com.stefanini.hackaton.rest.parsers.ContaParser;
import com.stefanini.hackaton.rest.persistence.ContaDAO;

public class ContaService {

	@Inject
	ContaDAO dao;

	@Inject
	ContaParser parser;

	public List<Conta> listAll() {
		return dao.listAll();
	}

	public Conta findById(Integer id) {
		return dao.findById(id);
	}

	public Conta findByAgenciaConta(String nrAgencia, String nrConta) {
		return dao.findByAgenciaConta(nrAgencia, nrConta);
	}

	// TODO Validação de Agencia e Numero de conta
	@Transactional
	public boolean insertSingle(Conta conta) {
		dao.insert(conta);
		return true;
	}

	@Transactional
	public boolean updateConta(Conta contaPersistida) {
		dao.update(contaPersistida);
		return true;
	}

	@Transactional
	public boolean removeById(Integer id) {
		dao.removeById(id);
		return true;
	}

	@Transactional
	public boolean associateToPessoa(Integer id, String cpf) {
		// TODO implementar
		// Quais verificações fazer? se a pessoa existe. se existe, associa
		// conta (presume-se que a conta existe com id e tudo mais)
		dao.associate(id, cpf);
		return true;
	}

}
