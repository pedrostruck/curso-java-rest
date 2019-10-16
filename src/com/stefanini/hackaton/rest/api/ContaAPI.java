package com.stefanini.hackaton.rest.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.rest.dtos.LoginDTO;
import com.stefanini.hackaton.rest.entidades.Conta;
import com.stefanini.hackaton.rest.exceptions.NegocioException;
import com.stefanini.hackaton.rest.service.ContaService;

@Path("/conta")
@Produces(MediaType.APPLICATION_JSON)
public class ContaAPI {

	@Inject
	ContaService contaService;

	@Path("/autenticar")
	@POST
	public Response login(LoginDTO dto) throws NegocioException {
		// TODO autenticar com a senha
		dto.setSenha(null);
		return Response.ok(dto).build();
	}

	@GET
	@Path("/all")
	public Response consultarAll() {
		return Response.ok(contaService.listAll()).build();
	}

	@GET
	@Path("/{id}")
	public Response consultarById(@PathParam("id") Integer id) {
		return Response.ok(contaService.findById(id)).build();
	}

	@Path("/{agencia}/{conta}")
	public Response consultar(@PathParam("agencia") String nrAgencia,
					@PathParam("conta") String nrConta) {
		return Response.ok(contaService.findByAgenciaConta(nrAgencia, nrConta))
						.build();
	}

	@POST
	@Path("/single")
	public Response inserir(Conta conta) {
		return Response.ok(contaService.insertSingle(conta)).build();
	}

	@POST
	@Path("/multi")
	public Response inserir(List<Conta> listaConta) {
		listaConta.forEach(conta -> {
			contaService.insertSingle(conta);
		});
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response excluir(@PathParam("id") Integer id) {
		return Response.ok(contaService.removeById(id)).build();
	}

//	@DELETE
//	public Response excluir2(@QueryParam("id") Integer id) {
//		return Response.ok().build();
//	}

	@PUT
	@Path("/update")
	public Response alterar(Conta conta) {
		Conta contaPersistida = contaService.findById(conta.getId());
		contaPersistida.setSenha(conta.getSenha());
		return Response.ok(contaService.updateConta(contaPersistida)).build();
	}

	@PUT
	@Path("/associar/{cpf}/{id}")
	public Response associarACpf(@PathParam("cpf") String cpf,
					@PathParam("id") Integer id) {
		return Response.ok(contaService.associateToPessoa(id, cpf)).build();
	}

}
