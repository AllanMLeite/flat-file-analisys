package com.ilegra.desafiotecnico.model;

public class Estatistica {

	public Estatistica(Long totalCompradores, Long totalVendedores, LinhaDadosVenda vendaComMaiorValor,
			LinhaDadosVendedor vendedorComMenosVendas) {
		this.totalCompradores = totalCompradores;
		this.totalVendedores = totalVendedores;
		this.vendaComMaiorValor = vendaComMaiorValor;
		this.vendedorComMenosVendas = vendedorComMenosVendas;
	}

	private Long totalCompradores;
	private Long totalVendedores;
	private LinhaDadosVenda vendaComMaiorValor;
	private LinhaDadosVendedor vendedorComMenosVendas;

	@Override
	public String toString() {
		return totalCompradores + ";" + totalVendedores + ";" + vendaComMaiorValor.getIdVenda() + ";"
				+ vendedorComMenosVendas.getNome();
	}
}
