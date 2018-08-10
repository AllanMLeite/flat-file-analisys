package com.ilegra.desafiotecnico.model;

import java.util.List;

import com.ilegra.desafiotecnico.exception.DomainException;

public class LinhaDadosVenda extends Linha {

	public LinhaDadosVenda(String idVenda, List<Item> itens, String vendedor) throws DomainException {
		super();
		validarIdVenda(idVenda);
		this.idVenda = Long.valueOf(idVenda);
		this.listaItens = itens;
		this.vendedor = vendedor;
	}

	private Integer idLinha = 3;

	private Long idVenda;
	private List<Item> listaItens;
	private String vendedor;

	public Integer getIdLinha() {
		return idLinha;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public List<Item> getListaItens() {
		return listaItens;
	}

	public String getVendedor() {
		return vendedor;
	}

	public Double getValorPedido() {
		return listaItens.stream().mapToDouble(x -> x.getQuantidade() * x.getPreco()).sum();
	}

	private void validarIdVenda(String idVenda) {
		// TODO Auto-generated method stub

	}
}
