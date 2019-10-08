package com.stefanini.hackathon.rest.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.stefanini.hackathon.rest.entidades.Conta;
import com.stefanini.hackathon.rest.entidades.Pessoa;

@Singleton
public class Repositorio {
	private Map<String, Pessoa> mapPessoa = new HashMap<>();
	private Map<Integer, Conta> mapConta = new HashMap<>();

	public Map<String, Pessoa> getMapPessoa() {
		return mapPessoa;
	}

	public Map<Integer, Conta> getMapConta() {
		return mapConta;
	}

	public void setMapConta(Map<Integer, Conta> mapConta) {
		this.mapConta = mapConta;
	}

	public void setMapPessoa(Map<String, Pessoa> mapPessoa) {
		this.mapPessoa = mapPessoa;
	}

}
