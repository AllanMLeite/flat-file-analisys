package com.ilegra.desafiotecnico.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ArquivoPosicoesConfig arquivoPosicoesConfig;
	
	@Override
	public Linha converter(String[] dados) throws DomainException {
		String idVenda = dados[arquivoPosicoesConfig.getPosicaoVendaId()];
		String itensDesnormalizados = dados[arquivoPosicoesConfig.getPosicaoVendaItens()];
		String vendedor = dados[arquivoPosicoesConfig.getPosicaoVendaVendedor()];

		List<Item> itens = normalizarItens(itensDesnormalizados);

		return new LinhaDadosVenda(idVenda, itens, vendedor);
	}

	private List<Item> normalizarItens(String itensDesnormalizados) throws DomainException {
		List<Item> itensNormalizados = new ArrayList<>();

		String[] listaItensDesnormalizados = itensDesnormalizados.replace(SEPARADOR_ITEM_INICIO, "")
				.replace(SEPARADOR_ITEM_FINAL, "").split(SEPARADOR_ITEM_VIRGULA);

		for (String item : listaItensDesnormalizados) {
			String[] propriedadesItem = item.split(SEPARADOR_ITEM_PROPRIEDADE);

			Item itemNormalizado = new Item(propriedadesItem[arquivoPosicoesConfig.getPosicaoVendaItemId()],
					propriedadesItem[arquivoPosicoesConfig.getPosicaoVendaItemQuantidade()],
					propriedadesItem[arquivoPosicoesConfig.getPosicaoVendaItemPreco()]);
			itensNormalizados.add(itemNormalizado);
		}

		return itensNormalizados;
	}
}
