package com.ilegra.desafiotecnico.model.enums;

import java.util.Arrays;
import java.util.List;

import com.ilegra.desafiotecnico.exception.DomainException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoDadoEnum {
	VENDEDOR("001", "linhaDadosVendedorConverter"), 
	COMPRADOR("002", "linhaDadosCompradorConverter"),
	VENDA("003", "linhaDadosVendaConverter");

	private String idLinha;
	
	@Getter
	private String converterId;

	public static TipoDadoEnum buscarPeloId(String id) throws DomainException {
		List<TipoDadoEnum> tipos = Arrays.asList(values());
		return tipos.stream()
				.filter(x -> x.idLinha.equals(id))
				.findFirst()
				.orElseThrow(() -> new DomainException("Id invalido."));
	}
}