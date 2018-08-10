package com.ilegra.desafiotecnico.model;

public class Estatistica {

	public Estatistica(Long totalCompradores, Long totalVendedores, Long idVendaComMaiorPreco,
			LinhaDadosVendedor vendedorComMenosVendas) {
		this.totalCompradores = totalCompradores;
		this.totalVendedores = totalVendedores;
		this.idVendaComMaiorPreco = idVendaComMaiorPreco;
		this.vendedorComMenosVendas = vendedorComMenosVendas;
	}

	private Long totalCompradores;
	private Long totalVendedores;
	private Long idVendaComMaiorPreco;
	private LinhaDadosVendedor vendedorComMenosVendas;

	@Override
	public String toString() {
		return totalCompradores + ";" + totalVendedores + ";" + idVendaComMaiorPreco + ";"
				+ vendedorComMenosVendas.getNome() + "(" + vendedorComMenosVendas.getCpf() + ")";
	}
}
