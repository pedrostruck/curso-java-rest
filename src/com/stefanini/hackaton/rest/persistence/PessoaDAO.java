package com.stefanini.hackaton.rest.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.stefanini.hackaton.rest.entidades.Pessoa;
import com.stefanini.hackaton.rest.exceptions.NegocioException;

public class PessoaDAO extends GenericDAO<Integer, Pessoa> {

	@SuppressWarnings("unchecked")
	public List<Pessoa> listAll() {
		return getEntityManager().createNamedQuery("Pessoa.findAll")
						.getResultList();
	}

	public Pessoa findByCpf(String cpf) throws NegocioException {
		try {
			return (Pessoa) getEntityManager()
							.createNamedQuery("Pessoa.findByCpf")
							.setParameter("cpf", cpf).getSingleResult();
		} catch (NoResultException e) {
			throw new NegocioException("Nenhum usuário encontrado!");
		}
	}

	@Transactional
	public Integer removeByCpf(String cpf) {
		return getEntityManager().createNamedQuery("Pessoa.removeByCpf")
						.setParameter("cpf", cpf).executeUpdate();
	}

}
