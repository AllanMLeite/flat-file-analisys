package com.ilegra.desafiotecnico.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.config.ArquivoPosicoesConfig;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Item;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosVenda;

@Component
public class LinhaDadosVendaConverter implements LinhaDadosConverter {

	private static final String SEPARADOR_ITEM_INICIO = "[";
	private static final String SEPARADOR_ITEM_FINAL = "]";
	private static final String SEPARADOR_ITEM_VIRGULA = ",";
	private static final String SEPARADOR_ITEM_PROPRIEDADE = "-";

	@Override
	public Linha converter(String[] dados) throws DomainException {
		String idVenda = dados[ArquivoPosicoesConfig.POSICAO_VENDA_ID];
		String itensDesnormalizados = dados[ArquivoPosicoesConfig.POSICAO_VENDA_ITENS];
		String vendedor = dados[ArquivoPosicoesConfig.POSICAO_VENDA_VENDEDOR];

		List<Item> itens = normalizarItens(itensDesnormalizados);

		return new LinhaDadosVenda(idVenda, itens, vendedor);
	}

	private List<Item> normalizarItens(String itensDesnormalizados) throws DomainException {
		List<Item> itensNormalizados = new ArrayList<>();

		String[] listaItensDesnormalizados = itensDesnormalizados.replace(SEPARADOR_ITEM_INICIO, "")
				.replace(SEPARADOR_ITEM_FINAL, "").split(SEPARADOR_ITEM_VIRGULA);

		for (String item : listaItensDesnormalizados) {
			String[] propriedadesItem = item.split(SEPARADOR_ITEM_PROPRIEDADE);

			Item itemNormalizado = new Item(propriedadesItem[ArquivoPosicoesConfig.POSICAO_VENDA_ITEM_ID],
					propriedadesItem[ArquivoPosicoesConfig.POSICAO_VENDA_ITEM_QUANTIDADE],
					propriedadesItem[ArquivoPosicoesConfig.POSICAO_VENDA_ITEM_PRECO]);
			itensNormalizados.add(itemNormalizado);
		}

		return itensNormalizados;
	}
}
