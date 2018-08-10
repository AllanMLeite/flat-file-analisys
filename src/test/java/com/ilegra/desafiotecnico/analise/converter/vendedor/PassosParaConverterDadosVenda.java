package com.ilegra.desafiotecnico.analise.converter.vendedor;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.converter.LinhaDadosVendaConverter;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.LinhaDadosVenda;

public class PassosParaConverterDadosVenda extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	LinhaDadosVendaConverter converter;

	private static final Integer POSICAO_ID_VENDA = 1;
	private static final Integer POSICAO_ITENS = 2;
	private static final Integer POSICAO_VENDEDOR = 3;

	private String[] dados = new String[10];
	private String msgErro;
	private LinhaDadosVenda linha;

	public PassosParaConverterDadosVenda() {

		Dado("^que informei um item com id \"([^\"]*)\", quantidade \"([^\"]*)\" e preco \"([^\"]*)\"$",
				(String id, String quantidade, String preco) -> {
					dados[POSICAO_ITENS] = "[" + id + "-" + quantidade + "-" + preco + "]";
				});

		Dado("^que informei o id \"([^\"]*)\"$", (String id) -> {
			dados[POSICAO_ID_VENDA] = id;
		});

		Quando("^converter os dados da venda$", () -> {
			try {
				linha = (LinhaDadosVenda) converter.converter(dados);
			} catch (DomainException e) {
				msgErro = e.getMessage();
			}
		});

		Entao("^deve exibir a mensagem  \"([^\"]*)\"$", (String esperado) -> {
			assertEquals(esperado, msgErro);
		});

	}
}
