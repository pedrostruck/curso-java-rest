package com.stefanini.hackathon.rest.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

import com.stefanini.hackathon.rest.dto.ContaDTO;
import com.stefanini.hackathon.rest.entidades.Conta;
import com.stefanini.hackathon.rest.entidades.Pessoa;
import com.stefanini.hackathon.rest.exceptions.NegocioException;
import com.stefanini.hackathon.rest.parsers.ContaParser;
import com.stefanini.hackathon.rest.persistence.Repositorio;

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class ContaAPI {

	@Inject
	Repositorio repositorio;

	private ContaParser contaParser = new ContaParser();

	@POST
	@Path("/addSingle")
	public Response inserirConta(Conta conta) throws NegocioException {
		if (repositorio.getMapConta().get(conta.getId()) != null) {
			throw new NegocioException("Já existe conta cadastrada com o id "
					+ conta.getId() + ".");
		}
		if (conta.isIncomplete()) {
			throw new NegocioException(
					"Existem campos vazios na conta a ser adicionada! Preencha todos os campos.");
		}
		repositorio.getMapConta().put(conta.getId(), conta);
		return getResponseWithDTO(repositorio);
	}

	@POST
	@Path("/addMultiple")
	public Response inserirContas(ArrayList<Conta> contas)
			throws NegocioException {
		for (Conta conta : contas) {
			if (repositorio.getMapConta().get(conta.getId()) != null) {
				throw new NegocioException(
						"Já existe conta cadastrada com o id " + conta.getId()
								+ ".");
			}
			if (conta.isIncomplete()) {
				throw new NegocioException(
						"Existem campos vazios na conta a ser adicionada! Preencha todos os campos.");
			}
			repositorio.getMapConta().put(conta.getId(), conta);
		}
		return getResponseWithDTO(repositorio);
	}

	@GET
	public Response getContas() throws NegocioException {
		if (repositorio.getMapConta().isEmpty()) {
			throw new NegocioException("Não existem contas cadastradas.");
		}
		return getResponseWithDTO(repositorio);
	}

	@GET
	@Path("/map")
	public Response getContasMap() throws NegocioException {
		if (repositorio.getMapConta().isEmpty()) {
			throw new NegocioException("Não existem contas cadastradas.");
		}
		return Response.ok(repositorio.getMapConta()).build();
	}

	@GET
	@Path("/{id}")
	public Response getContaByID(@PathParam("id") Integer id)
			throws NegocioException {
		Conta conta = repositorio.getMapConta().get(id);
		if (conta == null) {
			throw new NegocioException(
					"Não existe conta cadastrada com esse id.");
		}
		return Response.ok(contaParser.toDTO(conta)).build();
	}

	@GET
	@Path("/{agencia}/{numeroDaConta}")
	public Response getContaByAgenciaEConta(
			@PathParam("agencia") String agencia,
			@PathParam("numeroDaConta") String numeroDaConta)
			throws NegocioException {
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
		if (contaFetched == null) {
			throw new NegocioException(
					"Não existe conta cadastrada com essa agência e número de conta.");
		}
		return Response.ok(contaParser.toDTO(contaFetched)).build();
	}

	@PUT
	@Path("/associar/{cpf}/{id}")
	public Response alterarConta(@PathParam("cpf") String cpf,
			@PathParam("id") Integer id) throws NegocioException {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		Conta conta = repositorio.getMapConta().get(id);
		if (pessoa == null || conta == null) {
			throw new NegocioException("Pessoa e/ou Conta inexistente(s).");
		}
		pessoa.setConta(conta);
		return getResponseWithDTO(repositorio);
	}

	@PUT
	@Path("/associar/{cpf}/{id}")
	public Response associarContaAPessoa(@PathParam("cpf") String cpf,
			@PathParam("id") Integer id) throws NegocioException {
		Pessoa pessoa = repositorio.getMapPessoa().get(cpf);
		Conta conta = repositorio.getMapConta().get(id);
		if (pessoa == null || conta == null) {
			throw new NegocioException("Pessoa e/ou Conta inexistente(s).");
		}
		pessoa.setConta(conta);
		return getResponseWithDTO(repositorio);
	}

	@DELETE
	@Path("/{id}")
	public Response excluirPathParam(@PathParam("id") Integer id) {
		repositorio.getMapConta().remove(id);
		return getResponseWithDTO(repositorio);
	}

	@DELETE
	public Response excluirQueryParam(@QueryParam("id") Integer id) {
		repositorio.getMapConta().remove(id);
		return getResponseWithDTO(repositorio);
	}

	private Response getResponseWithDTO(Repositorio repositorio) {
		List<ContaDTO> listContasDTO = contaParser
				.toListDTO(repositorio.getMapConta());
		return Response.ok(listContasDTO).build();
	}
}
