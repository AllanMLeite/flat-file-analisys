package com.ilegra.desafiotecnico.converter;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosVendedor;

public class LinhaDadosVendedorConverter implements LinhaDadosConverter {

	private static final Integer POSICAO_CPF = 1;
	private static final Integer POSICAO_NOME = 2;
	private static final Integer POSICAO_SALARIO = 3;

	@Override
	public Linha converter(String[] dados) throws DomainException {
		String cpf = dados[POSICAO_CPF];
		String nome = dados[POSICAO_NOME];
		String salario = dados[POSICAO_SALARIO];

		return new LinhaDadosVendedor(cpf, nome, salario);
	}
}
