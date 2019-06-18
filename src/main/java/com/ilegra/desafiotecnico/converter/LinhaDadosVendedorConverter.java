package com.ilegra.desafiotecnico.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosVendedor;

@Component
public class LinhaDadosVendedorConverter implements LinhaDadosConverter {

	@Autowired
	private ArquivoPosicoesConfig arquivoPosicoesConfig;
	
	@Override
	public Linha converter(String[] dados) throws DomainException {
		String cpf = dados[arquivoPosicoesConfig.getPosicaoVendedorCpf()];
		String nome = dados[arquivoPosicoesConfig.getPosicaoVendedorNome()];
		String salario = dados[arquivoPosicoesConfig.getPosicaoVendedorSalario()];

		return new LinhaDadosVendedor(cpf, nome, salario);
	}
}
