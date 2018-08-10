package com.ilegra.desafiotecnico.converter;

import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosVendedor;

@Component
public class LinhaDadosVendedorConverter implements LinhaDadosConverter {

	@Override
	public Linha converter(String[] dados) throws DomainException {
		String cpf = dados[ArquivoPosicoesConfig.POSICAO_VENDEDOR_CPF];
		String nome = dados[ArquivoPosicoesConfig.POSICAO_VENDEDOR_NOME];
		String salario = dados[ArquivoPosicoesConfig.POSICAO_VENDEDOR_SALARIO];

		return new LinhaDadosVendedor(cpf, nome, salario);
	}
}
