package com.ilegra.desafiotecnico.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosComprador;

@Component
public class LinhaDadosCompradorConverter implements LinhaDadosConverter {

	@Autowired
	private ArquivoPosicoesConfig arquivoPosicoesConfig;
	
	@Override
	public Linha converter(String[] dados) throws DomainException {
		String cnpj = dados[arquivoPosicoesConfig.getPosicaoCompradorCnpj()];
		String nome = dados[arquivoPosicoesConfig.getPosicaoCompradorNome()];
		String area = dados[arquivoPosicoesConfig.getPosicaoCompradorAreaNegocio()];

		return new LinhaDadosComprador(cnpj, nome, area);
	}
}
