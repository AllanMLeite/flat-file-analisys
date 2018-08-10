package com.ilegra.desafiotecnico.analise.converter.vendedor;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.converter.LinhaDadosVendedorConverter;
import com.ilegra.desafiotecnico.model.LinhaDadosVendedor;

public class PassosParaConverterDadosVendedor extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	LinhaDadosVendedorConverter converter;

	private static final Integer POSICAO_CPF = 1;
	private static final Integer POSICAO_NOME = 2;
	private static final Integer POSICAO_SALARIO = 3;

	private String[] dados = new String[10];
	private String msgErro;
	private LinhaDadosVendedor linha;

	public PassosParaConverterDadosVendedor() {
		Dado("^que informei o cpf \"([^\"]*)\"$", (String cpf) -> {
			dados[POSICAO_CPF] = cpf;
		});
		
		Dado("^que informei o salario \"([^\"]*)\"$", (String salario) -> {
			dados[POSICAO_SALARIO] = salario;
		});

		Quando("^converter$", () -> {
			try {
				linha = (LinhaDadosVendedor) converter.converter(dados);
			} catch (Exception e) {
				msgErro = e.getMessage();
			}
		});

		Entao("^deve exibir a mensagem \"([^\"]*)\"$", (String esperado) -> {
			assertEquals(esperado, msgErro);
		});
	}
}
