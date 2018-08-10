package com.ilegra.desafiotecnico.analise.converter.comprador;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.converter.LinhaDadosCompradorConverter;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.LinhaDadosComprador;

public class PassosParaConverterDadosComprador extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	LinhaDadosCompradorConverter converter;

	private String[] dadosComprador = new String[10];
	private String msgErro;
	private LinhaDadosComprador linha;

	public PassosParaConverterDadosComprador() {
		Dado("^que informei o cnpj \"([^\"]*)\"$", (String cnpjInformado) -> {
			dadosComprador[ArquivoPosicoesConfig.POSICAO_COMPRADOR_CNPJ] = cnpjInformado;
		});

		Dado("^que informei o nome \"([^\"]*)\"$", (String nome) -> {
			dadosComprador[ArquivoPosicoesConfig.POSICAO_COMPRADOR_NOME] = nome;
		});

		Dado("^que informei a area de negocio \"([^\"]*)\"$", (String area) -> {
			dadosComprador[ArquivoPosicoesConfig.POSICAO_COMPRADOR_AREA_NEGOCIO] = area;
		});

		Entao("^deve gerar um comprador com nome \"([^\"]*)\", cnpj \"([^\"]*)\" e area \"([^\"]*)\"$",
				(String nome, String cnpj, String area) -> {
					assertEquals(nome, linha.getNome());
					assertEquals(cnpj, linha.getCnpj());
					assertEquals(area, linha.getAreaNegocio());
				});

		Quando("^converter$", () -> {
			try {
				linha = (LinhaDadosComprador) converter.converter(dadosComprador);
			} catch (DomainException e) {
				msgErro = e.getMessage();
			}
		});

		Entao("^deve exibir a mensagem \"([^\"]*)\"$", (String esperado) -> {
			assertEquals(esperado, msgErro);
		});
	}
}
