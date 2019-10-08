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
import com.stefanini.hackathon.rest.exceptions.NegocioException;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
public class ContaAPI {

	@Inject
	Repositorio repositorio;

	@GET
	public Response getContas() throws NegocioException {
		if (repositorio.getMapConta().isEmpty()) {
			throw new NegocioException("Não há contas cadastradas.");
		}
		return Response.ok(repositorio.getMapConta()).build();
	}

	@GET
	@Path("/{id}")
	public Response getContaByID(@PathParam("id") Integer id)
			throws NegocioException {
		Conta conta = repositorio.getMapConta().get(id);
		if (conta == null) {
			throw new NegocioException("Não há conta cadastrada com esse id.");
		}
		return Response.ok(conta).build();
	}

	@GET
	@Path("/{agencia}/{numeroDaConta}")
	public Response getContaByAgenciaEConta(
			@PathParam("agencia") String agencia,
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
	@Path("/addSingle")
	public Response inserirConta(Conta conta) throws NegocioException {
		if (repositorio.getMapConta().get(conta.getId()) != null) {
			throw new NegocioException("Já existe conta cadastrada com o id "
					+ conta.getId() + ".");
		}
		return Response.ok(repositorio.getMapConta()).build();
	}

	@POST
	@Path("/addMultiple")
	public Response inserirContas(ArrayList<Conta> contas) {
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
			@PathParam("id") Integer id) throws NegocioException {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		Conta conta = repositorio.getMapConta().get(id);
		if (pessoa == null || conta == null) {
			throw new NegocioException("Pessoa e/ou Conta inexistente(s).");
		}
		pessoa.setConta(conta);
		return Response.ok(repositorio.getMapPessoa()).build();
	}
}
