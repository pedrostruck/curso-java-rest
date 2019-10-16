package com.stefanini.hackaton.rest.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.stefanini.hackaton.rest.entidades.Pessoa;
import com.stefanini.hackaton.rest.exceptions.NegocioException;
import com.stefanini.hackaton.rest.parsers.PessoaParser;
import com.stefanini.hackaton.rest.persistence.PessoaDAO;

public class PessoaService {

	@Inject
	PessoaDAO dao;

	@Inject
	PessoaParser parser;

	public List<Pessoa> listAll() {
		return dao.listAll();
	}

	public Pessoa findByCpf(String cpf) {
		Pessoa p = null;
		try {
			p = dao.findByCpf(cpf);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Transactional
	public boolean insertSingle(Pessoa pessoa) {
		dao.insert(pessoa);
		return true;
	}

	@Transactional
	public boolean removeByCpf(String cpf) {
		dao.removeByCpf(cpf);
		return true;
	}

	@Transactional
	public boolean updatePessoa(Pessoa pessoaPersistida) {
		dao.update(pessoaPersistida);
		return true;
	}
}
