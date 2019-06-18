package com.ilegra.desafiotecnico.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ilegra.desafiotecnico.model.Estatistica;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.LinhaDadosComprador;
import com.ilegra.desafiotecnico.model.LinhaDadosVenda;
import com.ilegra.desafiotecnico.model.LinhaDadosVendedor;

@Service
public class EstatisticaService {

	public Estatistica analisarDados(List<Linha> linhasDoArquivoNormalizadas) {
		Long totalCompradores = analisarTotalCompradores(linhasDoArquivoNormalizadas);
		Long totalVendedores = analisarTotalVendedores(linhasDoArquivoNormalizadas);
		Long idVendaComMaiorPreco = analisarVendaComMaiorPreco(linhasDoArquivoNormalizadas);
		LinhaDadosVendedor vendedorComMenosVendas = analisarVendedorComMenosVendas(linhasDoArquivoNormalizadas);
		return new Estatistica(totalCompradores, totalVendedores, idVendaComMaiorPreco, vendedorComMenosVendas);
	}

	private long analisarTotalVendedores(List<Linha> linhasDoArquivoNormalizadas) {
		return linhasDoArquivoNormalizadas.stream()
				.filter(LinhaDadosVendedor.class::isInstance)
				.count();
	}

	private long analisarTotalCompradores(List<Linha> linhasDoArquivoNormalizadas) {
		return linhasDoArquivoNormalizadas.stream()
				.filter(LinhaDadosComprador.class::isInstance)
				.count();
	}

	private Long analisarVendaComMaiorPreco(List<Linha> linhasDoArquivoNormalizadas) {
		List<LinhaDadosVenda> vendas = getVendas(linhasDoArquivoNormalizadas);

		if (vendas.isEmpty())
			return 0l;

		return Collections.max(vendas, Comparator.comparing(LinhaDadosVenda::getValorPedido)).getIdVenda();
	}

	private LinhaDadosVendedor analisarVendedorComMenosVendas(List<Linha> linhasDoArquivoNormalizadas) {
		List<LinhaDadosVendedor> vendedores = getVendedores(linhasDoArquivoNormalizadas);

		List<LinhaDadosVenda> vendas = getVendas(linhasDoArquivoNormalizadas);

		Map<LinhaDadosVendedor, Double> relacaoDeVendasPorVendedor = new HashMap<>();

		vendedores.forEach(vendedor -> {
			relacaoDeVendasPorVendedor.put(vendedor, getTotalVendasDoVendedor(vendas, vendedor));
		});

		return Collections.min(relacaoDeVendasPorVendedor.entrySet(), Comparator.comparing(Entry::getValue)).getKey();
	}

	private Double getTotalVendasDoVendedor(List<LinhaDadosVenda> vendas, LinhaDadosVendedor vendedor) {
		List<LinhaDadosVenda> vendasDoVendedor = vendas.stream()
				.filter(x -> x.getVendedor().equals(vendedor.getNome()))
				.collect(Collectors.toList());

		return vendasDoVendedor.stream()
				.mapToDouble(LinhaDadosVenda::getValorPedido)
				.sum();
	}

	private List<LinhaDadosVendedor> getVendedores(List<Linha> linhasDoArquivoNormalizadas) {
		return linhasDoArquivoNormalizadas.stream()
				.filter(LinhaDadosVendedor.class::isInstance)
				.map(x -> (LinhaDadosVendedor) x)
				.collect(Collectors.toList());
	}

	private List<LinhaDadosVenda> getVendas(List<Linha> linhasDoArquivoNormalizadas) {
		return linhasDoArquivoNormalizadas.stream()
				.filter(LinhaDadosVenda.class::isInstance)
				.map(x -> (LinhaDadosVenda) x)
				.collect(Collectors.toList());
	}

}
