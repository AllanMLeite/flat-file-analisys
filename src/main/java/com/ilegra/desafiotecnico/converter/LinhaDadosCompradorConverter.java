package com.ilegra.desafiotecnico.converter;

import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosComprador;

@Component
public class LinhaDadosCompradorConverter implements LinhaDadosConverter {

	@Override
	public Linha converter(String[] dados) throws DomainException {
		String cnpj = dados[ArquivoPosicoesConfig.SEPARADOR_COMPRADOR_CNPJ];
		String nome = dados[ArquivoPosicoesConfig.SEPARADOR_COMPRADOR_NOME];
		String area = dados[ArquivoPosicoesConfig.SEPARADOR_COMPRADOR_AREA_NEGOCIO];

		return new LinhaDadosComprador(cnpj, nome, area);
	}
}
