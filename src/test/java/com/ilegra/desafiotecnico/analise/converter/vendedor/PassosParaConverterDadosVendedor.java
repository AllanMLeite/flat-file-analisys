package com.ilegra.desafiotecnico.analise.converter.vendedor;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.converter.LinhaDadosVendedorConverter;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.LinhaDadosVendedor;

public class PassosParaConverterDadosVendedor extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	private LinhaDadosVendedorConverter converter;
	
	@Autowired
	private ArquivoPosicoesConfig arquivoPosicoesConfig;

	private String[] dados = new String[10];
	private String msgErro;
	private LinhaDadosVendedor linha;

	public PassosParaConverterDadosVendedor() {
		Dado("^que informei o cpf \"([^\"]*)\"$", (String cpf) -> {
			dados[arquivoPosicoesConfig.getPosicaoVendedorCpf()] = cpf;
		});

		Dado("^que informei o salario \"([^\"]*)\"$", (String salario) -> {
			dados[arquivoPosicoesConfig.getPosicaoVendedorSalario()] = salario;
		});

		Dado("^que informei o nome do vendedor \"([^\"]*)\"$", (String nome) -> {
			dados[arquivoPosicoesConfig.getPosicaoVendedorNome()] = nome;
		});

		Entao("^deve gerar um vendedor com nome \"([^\"]*)\", cpf \"([^\"]*)\" e salario \"([^\"]*)\"$",
				(String nome, String cpf, String salario) -> {
					assertEquals(nome, linha.getNome());
					assertEquals(cpf, linha.getCpf());
					assertEquals(salario, linha.getSalario().toString());
				});

		Quando("^converter os dados$", () -> {
			try {
				linha = (LinhaDadosVendedor) converter.converter(dados);
			} catch (DomainException e) {
				msgErro = e.getMessage();
			}
		});

		Entao("^deve exibir  a mensagem \"([^\"]*)\"$", (esperado) -> {
			assertEquals(esperado, msgErro);
		});
	}
}
