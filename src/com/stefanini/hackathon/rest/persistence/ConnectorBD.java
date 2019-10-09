package com.stefanini.hackathon.rest.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.stefanini.hackathon.rest.entidades.Pessoa;

public class ConnectorBD {
	@PersistenceContext(unitName = "bancoHackatonUnit")
	EntityManager em;

	public Pessoa getPessoa() {
		return em.find(Pessoa.class, 1);
	}
}
