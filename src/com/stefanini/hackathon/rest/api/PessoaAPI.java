package com.stefanini.hackathon.rest.api;

import java.util.ArrayList;
import java.util.List;

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

import com.stefanini.hackathon.rest.dto.PessoaDTO;
import com.stefanini.hackathon.rest.entidades.Pessoa;
import com.stefanini.hackathon.rest.exceptions.NegocioException;
import com.stefanini.hackathon.rest.parsers.PessoaParser;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class PessoaAPI {

	@Inject
	private Repositorio repositorio;

	private PessoaParser pessoaParser = new PessoaParser();

	@POST
	@Path("/addSingle")
	public Response insertPessoa(Pessoa pessoa) throws NegocioException {
		if (repositorio.getMapPessoa().get(pessoa.getCpf()) != null) {
			throw new NegocioException("Já existe pessoa cadastrada com o CPF "
					+ pessoa.getCpf() + ".");
		}
		if (pessoa.isIncomplete()) {
			throw new NegocioException(
					"Existem campos vazios nas informações da pessoa a ser adicionada! Preencha todos os campos.");
		}
		repositorio.getMapPessoa().put(pessoa.getCpf(), pessoa);
		return getResponseWithDTO(repositorio);
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
		return getResponseWithDTO(repositorio);
	}

	@GET
	@Path("/{cpf}")
	public Response getPessoaByCPF(@PathParam("cpf") String cpf)
			throws NegocioException {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		if (pessoa == null) {
			throw new NegocioException(
					"Não existe pessoa cadastrada com este CPF.");
		}
		return Response.ok(pessoaParser.toDTO(pessoa)).build();
	}

	@GET
	public Response getPessoas() throws NegocioException {
		if (repositorio.getMapPessoa().isEmpty()) {
			throw new NegocioException("Não existem pessoas cadastradas.");
		}
		return getResponseWithDTO(repositorio);
	}

	@GET
	@Path("/map")
	public Response getPessoasMap() throws NegocioException {
		if (repositorio.getMapPessoa().isEmpty()) {
			throw new NegocioException("Não existem pessoas cadastradas.");
		}
		return Response.ok(repositorio.getMapPessoa()).build();
	}

	@PUT
	@Path("/{cpf}")
	public Response alterar(Pessoa pessoa, @PathParam("cpf") String cpf)
			throws NegocioException {
		Pessoa pessoaFetched = repositorio.getMapPessoa().get(cpf);
		if (pessoaFetched == null) {
			throw new NegocioException(
					"Não existe pessoa cadastrada com este CPF.");
		}
		if (cpf != pessoa.getCpf()) {
			throw new NegocioException("O CPF não pode ser alterado.");
		}
		pessoa.setCpf(cpf);
		repositorio.getMapPessoa().put(cpf, pessoa);
		return getResponseWithDTO(repositorio);
	}

	@DELETE
	@Path("/{cpf}")
	public Response excluirPathParam(@PathParam("cpf") String cpf) {
		repositorio.getMapPessoa().remove(cpf);
		return getResponseWithDTO(repositorio);
	}

	@DELETE
	public Response excluirQueryParam(@QueryParam("cpf") String cpf) {
		repositorio.getMapPessoa().remove(cpf);
		return getResponseWithDTO(repositorio);
	}

	private Response getResponseWithDTO(Repositorio repositorio) {
		List<PessoaDTO> listPessoasDTO = pessoaParser
				.toListDTO(repositorio.getMapPessoa());
		return Response.ok(listPessoasDTO).build();
	}

}
