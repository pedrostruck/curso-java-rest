package com.stefanini.hackaton.rest.parsers;

import com.stefanini.hackaton.rest.dtos.PessoaDTO;
import com.stefanini.hackaton.rest.entidades.Pessoa;

public class PessoaParser extends AbstractParser<Pessoa, PessoaDTO> {

	@Override
	public Pessoa toEntity(PessoaDTO dto) {
		return null;
	}

	@Override
	public PessoaDTO toDTO(Pessoa pessoa) {
		PessoaDTO dto = new PessoaDTO();
		dto.setNome(pessoa.getNome());
		return dto;
	}

}
