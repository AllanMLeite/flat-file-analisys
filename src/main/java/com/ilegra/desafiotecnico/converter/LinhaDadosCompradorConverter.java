package com.ilegra.desafiotecnico.converter;

import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosComprador;

@Component
public class LinhaDadosCompradorConverter implements LinhaDadosConverter {

	private static final Integer POSICAO_CNPJ = 1;
	private static final Integer POSICAO_NOME = 2;
	private static final Integer POSICAO_AREA_NEGOCIO = 3;

	@Override
	public Linha converter(String[] dados) throws DomainException {
		String cnpj = dados[POSICAO_CNPJ];
		String nome = dados[POSICAO_NOME];
		String area = dados[POSICAO_AREA_NEGOCIO];

		return new LinhaDadosComprador(cnpj, nome, area);
	}

}
