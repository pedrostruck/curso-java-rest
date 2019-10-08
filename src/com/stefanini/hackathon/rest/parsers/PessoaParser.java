package com.stefanini.hackathon.rest.parsers;

import com.stefanini.hackathon.rest.dto.PessoaDTO;
import com.stefanini.hackathon.rest.entidades.Pessoa;

public class PessoaParser extends AbstractParser<Pessoa, PessoaDTO> {

	@Override
	public Pessoa toEntity(PessoaDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PessoaDTO toDTO(Pessoa pessoa) {
		PessoaDTO dto = new PessoaDTO();
		dto.setNome(pessoa.getNome());
		return null;
	}

}
