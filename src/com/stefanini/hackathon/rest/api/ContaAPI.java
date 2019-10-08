package com.stefanini.hackathon.rest.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackathon.rest.entidades.Conta;
import com.stefanini.hackathon.rest.entidades.Pessoa;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
public class ContaAPI {

	@Inject
	Repositorio repositorio;

	@GET
	public Response get() {
		return Response.ok(repositorio.getMapConta()).build();
	}

	@GET
	@Path("/{id}")
	public Response getPessoaByCPF(@PathParam("id") Integer id) {
		Conta conta = repositorio.getMapConta().get(id);
		return Response.ok(conta).build();
	}

	@GET
	@Path("/{agencia}/{numeroDaConta}")
	public Response getPessoaByCPF(@PathParam("agencia") String agencia,
			@PathParam("numeroDaConta") String numeroDaConta) {
		Conta contaFetched = null;
		Iterator<Entry<Integer, Conta>> itr = repositorio.getMapConta()
				.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, Conta> entry = itr.next();
			Conta currentConta = entry.getValue();
			if (currentConta.getAgencia().equals(agencia)
					&& currentConta.getNumeroDaConta().equals(numeroDaConta)) {
				contaFetched = currentConta;
			}
		}
		return Response.ok(contaFetched).build();
	}

	@POST
	public Response inserir(ArrayList<Conta> contas) {
		for (Conta conta : contas) {
			repositorio.getMapConta().put(conta.getId(), conta);
		}
		return Response.ok(repositorio.getMapConta()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluirPathParam(@PathParam("id") Integer id) {
		repositorio.getMapConta().remove(id);
		return Response.ok(repositorio.getMapConta()).build();
	}

	@DELETE
	public Response excluirQueryParam(@QueryParam("id") Integer id) {
		repositorio.getMapConta().remove(id);
		return Response.ok(repositorio.getMapConta()).build();
	}

	@PUT
	@Path("/associar/{cpf}/{id}")
	public Response alterar(@PathParam("cpf") String cpf,
			@PathParam("id") Integer id) {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		Conta conta = repositorio.getMapConta().get(id);
		pessoa.setConta(conta);
		return Response.ok(repositorio.getMapPessoa()).build();
	}
}
