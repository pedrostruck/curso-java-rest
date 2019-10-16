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

import com.stefanini.hackaton.rest.entidades.Pessoa;
import com.stefanini.hackaton.rest.exceptions.NegocioException;
import com.stefanini.hackaton.rest.service.PessoaService;

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
public class PessoaAPI {

	@Inject
	PessoaService pessoaService;

	@GET
	@Path("/all")
	public Response consultar() throws NegocioException {
		return Response.ok(pessoaService.listAll()).build();
	}

	@GET
	@Path("/{cpf}")
	public Response consultar(@PathParam("cpf") String cpf)
					throws NegocioException {
		return Response.ok(pessoaService.findByCpf(cpf)).build();
	}

	@POST
	@Path("/single")
	public Response inserir(Pessoa pessoa) {
		return Response.ok(pessoaService.insertSingle(pessoa)).build();
	}

	@POST
	@Path("/multi")
	public Response inserir(List<Pessoa> listaPessoa) {
		listaPessoa.forEach(pessoa -> {
			pessoaService.insertSingle(pessoa);
		});
		return Response.ok().build();
	}

	@DELETE
	@Path("/{cpf}")
	public Response excluir(@PathParam("cpf") String cpf) {
		return Response.ok(pessoaService.removeByCpf(cpf)).build();
	}

//	@DELETE
//	public Response excluir2(@QueryParam("cpf") String cpf) {
//		return Response.ok(dao.excluir(cpf)).build();
//	}

	@PUT
	@Path("/update")
	public Response alterar(Pessoa pessoa) throws NegocioException {
		Pessoa pessoaPersistida = pessoaService.findByCpf(pessoa.getCpf());
		pessoaPersistida.setNome(pessoa.getNome());
		pessoaPersistida.setCpf(pessoa.getCpf());
		return Response.ok(pessoaService.updatePessoa(pessoaPersistida))
						.build();
	}

}
