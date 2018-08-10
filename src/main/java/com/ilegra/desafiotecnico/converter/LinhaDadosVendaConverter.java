package com.ilegra.desafiotecnico.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Item;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosVenda;

public class LinhaDadosVendaConverter implements LinhaDadosConverter {

	private static final Integer POSICAO_ID_VENDA = 1;
	private static final Integer POSICAO_ITENS = 2;
	private static final Integer POSICAO_VENDEDOR = 3;

	private static final Integer POSICAO_ITEM_ID = 0;
	private static final Integer POSICAO_ITEM_QUANTIDADE = 1;
	private static final Integer POSICAO_ITEM_PRECO = 2;

	@Override
	public Linha converter(String[] dados) throws DomainException {
		String idVenda = dados[POSICAO_ID_VENDA];
		String itensDesnormalizados = dados[POSICAO_ITENS];
		String vendedor = dados[POSICAO_VENDEDOR];

		List<Item> itens = normalizarItens(itensDesnormalizados);

		return new LinhaDadosVenda(idVenda, itens, vendedor);
	}

	private List<Item> normalizarItens(String listaItensDesnormalizados) {
		List<Item> itensNormalizados = new ArrayList<>();
		Arrays.asList(listaItensDesnormalizados.replace("[", "").replace("]", "").split(",")).forEach(item -> {
			String[] propriedadesItem = item.split("-");
			Item itemNormalizado = new Item(propriedadesItem[POSICAO_ITEM_ID],
					propriedadesItem[POSICAO_ITEM_QUANTIDADE], propriedadesItem[POSICAO_ITEM_PRECO]);
			itensNormalizados.add(itemNormalizado);
		});
		return itensNormalizados;
	}

}
