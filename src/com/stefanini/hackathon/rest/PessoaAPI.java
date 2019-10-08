package com.stefanini.hackathon.rest;

import java.util.ArrayList;

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

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaAPI {

	@Inject
	Repositorio repositorio;

	@GET
	public Response getPessoasMap() {
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@GET
	@Path("/{cpf}")
	public Response getPessoaByCPF(@PathParam("cpf") String cpf) {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		return Response.ok(pessoa).build();
	}

	@POST
	public Response insertPessoas(ArrayList<Pessoa> pessoas) {
		for (Pessoa pessoa : pessoas) {
			repositorio.getMapPessoa().put(pessoa.getCpf(), pessoa);
		}
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@DELETE
	@Path("/{cpf}")
	public Response excluirPathParam(@PathParam("cpf") String cpf) {
		repositorio.getMapPessoa().remove(cpf);
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@DELETE
	public Response excluirQueryParam(@QueryParam("cpf") String cpf) {
		repositorio.getMapPessoa().remove(cpf);
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@PUT
	@Path("/{cpf}")
	public Response alterar(Pessoa pessoa, @PathParam("cpf") String cpf) {
		pessoa.setCpf(cpf);
		repositorio.getMapPessoa().put(cpf, pessoa);
		return Response.ok(repositorio.getMapPessoa()).build();
	}

}
