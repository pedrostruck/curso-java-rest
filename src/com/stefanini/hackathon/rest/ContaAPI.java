package com.stefanini.hackathon.rest;

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

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
public class ContaAPI {
	@Inject
	Repositorio repositorio;

	@GET
	public Response get() {
		return Response.ok(repositorio.getMapConta()).build();
	}

	@POST
	public Response inserir(Pessoa pessoa, Conta conta) {
		repositorio.getMapConta().put(conta.getId(), conta);
		pessoa.setConta(conta);
		repositorio.getMapPessoa().put(pessoa.getCpf(), pessoa);
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluirPathParam(@PathParam("id") Integer id) {
		repositorio.getMapConta().remove(id);
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@DELETE
	public Response excluirQueryParam(@QueryParam("id") Integer id) {
		repositorio.getMapConta().remove(id);
		return Response.ok(repositorio.getMapPessoa()).build();
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
