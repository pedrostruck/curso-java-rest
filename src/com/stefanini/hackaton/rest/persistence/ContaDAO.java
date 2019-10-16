package com.stefanini.hackaton.rest.persistence;

import java.util.List;

import com.stefanini.hackaton.rest.entidades.Conta;

public class ContaDAO extends GenericDAO<Integer, Conta> {

	@SuppressWarnings("unchecked")
	public List<Conta> listAll() {
		return getEntityManager().createNamedQuery("Conta.findAll")
						.getResultList();
	}

//	public Conta findById(String id) throws NegocioException {
//		try {
//			return (Conta) getEntityManager()
//					.createNamedQuery("Conta.findById")
//					.setParameter("id", id).getSingleResult();
//		} catch (NoResultException e) {
//			throw new NegocioException("Nenhuma conta encontrada!");
//		}
//	}

	public Integer removeById(Integer id) {
		return getEntityManager().createNamedQuery("Conta.removeById")
						.setParameter("id", id).executeUpdate();
	}

	public Conta findByAgenciaConta(String nrAgencia, String nrConta) {
		return (Conta) getEntityManager()
						.createNamedQuery("Conta.findByAgenciaConta")
						.setParameter("nrAgencia", nrAgencia)
						.setParameter("nrConta", nrConta).getSingleResult();
	}

	public void associate(Integer id, String cpf) {

		getEntityManager().createNamedQuery("Conta.associateContaPessoa")
						.setParameter("idConta", id).setParameter("cpf", cpf)
						.getSingleResult();
	}

}
