package com.ilegra.desafiotecnico.analise.converter.venda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.converter.LinhaDadosVendaConverter;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Item;
import com.ilegra.desafiotecnico.model.LinhaDadosVenda;

import cucumber.api.DataTable;

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

		Dado("^que foram realizadas vendas$", () -> {
			dados[POSICAO_ITENS] = "[";
		});

		Dado("^que informei um item com id \"([^\"]*)\", quantidade \"([^\"]*)\" e preco \"([^\"]*)\"$",
				(String id, String quantidade, String preco) -> {

					if (dados[POSICAO_ITENS].length() > 1)
						dados[POSICAO_ITENS] = dados[POSICAO_ITENS] + ",";

					dados[POSICAO_ITENS] = dados[POSICAO_ITENS] + id + "-" + quantidade + "-" + preco;
				});

		Dado("^que informei o id \"([^\"]*)\"$", (String id) -> {
			dados[POSICAO_ID_VENDA] = id;
		});

		Dado("^que informei o vendedor \"([^\"]*)\"$", (String vendedor) -> {
			dados[POSICAO_VENDEDOR] = vendedor;
		});

		Quando("^converter os dados da venda$", () -> {
			dados[POSICAO_ITENS] = dados[POSICAO_ITENS] + "]";
			try {
				linha = (LinhaDadosVenda) converter.converter(dados);
			} catch (DomainException e) {
				msgErro = e.getMessage();
			}
		});

		Entao("^deve gerar uma venda id \"([^\"]*)\", vendedor \"([^\"]*)\" e com os itens abaixo$",
				(Long idVenda, String vendedor, DataTable datatableItens) -> {
					assertEquals(idVenda, linha.getIdVenda());
					assertEquals(vendedor, linha.getVendedor());

					List<Item> itens = datatableItens.asList(Item.class);

					itens.forEach(item -> assertTrue(linha.getListaItens().contains(item)));
				});

		Entao("^deve exibir a mensagem  \"([^\"]*)\"$", (String esperado) -> {
			assertEquals(esperado, msgErro);
		});
	}
}
