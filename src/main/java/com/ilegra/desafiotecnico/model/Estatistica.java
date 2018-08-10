package com.ilegra.desafiotecnico.model;

public class Estatistica {

	public Estatistica(Long totalClientes, Long totalVendedores, LinhaDadosVenda vendaComMaiorValor,
			LinhaDadosVendedor vendedorComMenosVendas) {
		this.totalClientes = totalClientes;
		this.totalVendedores = totalVendedores;
		this.vendaComMaiorValor = vendaComMaiorValor;
		this.vendedorComMenosVendas = vendedorComMenosVendas;
	}

	private Long totalClientes;
	private Long totalVendedores;
	private LinhaDadosVenda vendaComMaiorValor;
	private LinhaDadosVendedor vendedorComMenosVendas;

	@Override
	public String toString() {
		return totalClientes + ";" + totalVendedores + ";" + vendaComMaiorValor.getIdVenda() + ";"
				+ vendedorComMenosVendas.getNome();
	}
}
