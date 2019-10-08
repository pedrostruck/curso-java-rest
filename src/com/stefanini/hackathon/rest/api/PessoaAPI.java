package com.stefanini.hackathon.rest.api;

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

import com.stefanini.hackathon.rest.entidades.Pessoa;
import com.stefanini.hackathon.rest.exceptions.NegocioException;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaAPI {

	@Inject
	Repositorio repositorio;

	@GET
	public Response getPessoas() throws NegocioException {
		if (repositorio.getMapPessoa().isEmpty()) {
			throw new NegocioException("Não há pessoas cadastradas.");
		}
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@GET
	@Path("/{cpf}")
	public Response getPessoaByCPF(@PathParam("cpf") String cpf)
			throws NegocioException {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		if (pessoa == null) {
			throw new NegocioException(
					"Não há pessoa cadastrada com este CPF.");
		}
		return Response.ok(pessoa).build();
	}

	@POST
	@Path("/addSingle")
	public Response insertPessoa(Pessoa pessoa) throws NegocioException {
		if (repositorio.getMapPessoa().get(pessoa.getCpf()) != null) {
			throw new NegocioException("Já existe pessoa cadastrada com o CPF "
					+ pessoa.getCpf() + ".");
		}
		repositorio.getMapPessoa().put(pessoa.getCpf(), pessoa);
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@POST
	@Path("/addMultiple")
	public Response insertPessoas(ArrayList<Pessoa> pessoas)
			throws NegocioException {
		for (Pessoa pessoa : pessoas) {
			if (repositorio.getMapPessoa().get(pessoa.getCpf()) != null) {
				throw new NegocioException(
						"Já existe pessoa cadastrada com o CPF "
								+ pessoa.getCpf() + ".");
			}
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
