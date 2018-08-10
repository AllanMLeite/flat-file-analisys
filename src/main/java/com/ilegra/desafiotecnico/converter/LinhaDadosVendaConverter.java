package com.ilegra.desafiotecnico.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Item;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosVenda;

@Component
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

	private List<Item> normalizarItens(String itensDesnormalizados) throws DomainException {
		List<Item> itensNormalizados = new ArrayList<>();

		String[] listaItensDesnormalizados = itensDesnormalizados.replace("[", "").replace("]", "").split(",");

		for (String item : listaItensDesnormalizados) {
			String[] propriedadesItem = item.split("-");

			Item itemNormalizado = new Item(propriedadesItem[POSICAO_ITEM_ID],
					propriedadesItem[POSICAO_ITEM_QUANTIDADE], propriedadesItem[POSICAO_ITEM_PRECO]);
			itensNormalizados.add(itemNormalizado);
		}

		return itensNormalizados;
	}
}
