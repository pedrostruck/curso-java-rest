package com.stefanini.hackathon.rest.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractParser<E, DTO> {
	
	public abstract E toEntity(DTO dto);
	public abstract DTO toDTO(E e);

	public List<E> toListEntity(List<DTO> listDTO) {
		List<E> listEntity = new ArrayList<>();
		
		for (DTO dto : listDTO) {
			listEntity.add(toEntity(dto));
		}
		
		return listEntity;
	}
	
	public List<DTO> toListDTO(List<E> listEntity) {
		List<DTO> listDTO = new ArrayList<>();
		
		for (E e : listEntity) {
			listDTO.add(toDTO(e));
		}
		
		return listDTO;
	}
	
	public List<DTO> toMapDTO(Map<?, E> mapEntity) {
		List<DTO> list = new ArrayList<>();
		
		for (Entry<?, E> e : mapEntity.entrySet()) {
			list.add(toDTO(e.getValue()));
		}
		
		return list;
	}
	
	
	
	
	
	
	
}
