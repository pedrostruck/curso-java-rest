package com.stefanini.hackathon.rest.parsers;

import com.stefanini.hackathon.rest.dtos.ContaDTO;
import com.stefanini.hackathon.rest.entidades.Conta;

public class ContaParser extends AbstractParser<Conta, ContaDTO> {

	@Override
	public Conta toEntity(ContaDTO dto) {
		return null;
	}

	@Override
	public ContaDTO toDTO(Conta conta) {
		ContaDTO dto = new ContaDTO();
		dto.setAgencia(conta.getAgencia());
		dto.setConta(conta.getConta());
		return dto;
	}

}
