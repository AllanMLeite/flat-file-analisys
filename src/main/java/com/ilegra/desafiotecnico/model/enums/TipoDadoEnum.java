package com.ilegra.desafiotecnico.model.enums;

import java.util.Arrays;
import java.util.List;

import com.ilegra.desafiotecnico.converter.LinhaDadosCompradorConverter;
import com.ilegra.desafiotecnico.converter.LinhaDadosConverter;
import com.ilegra.desafiotecnico.converter.LinhaDadosVendaConverter;
import com.ilegra.desafiotecnico.converter.LinhaDadosVendedorConverter;
import com.ilegra.desafiotecnico.exception.DomainException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoDadoEnum {
	VENDEDOR("001", new LinhaDadosVendedorConverter()), 
	COMPRADOR("002", new LinhaDadosCompradorConverter()),
	VENDA("003", new LinhaDadosVendaConverter());

	private String idLinha;
	
	@Getter
	private LinhaDadosConverter converter;

	public static TipoDadoEnum buscarPeloId(String id) throws DomainException {
		List<TipoDadoEnum> tipos = Arrays.asList(values());
		return tipos.stream()
				.filter(x -> x.idLinha.equals(id))
				.findFirst()
				.orElseThrow(() -> new DomainException("Id invalido."));
	}
}