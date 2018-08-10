package com.ilegra.desafiotecnico.model;

public class Item {

	public Item(String idItem, String quantidade, String preco) {
		validar(idItem, quantidade, preco);
		this.idItem = Long.valueOf(idItem);
		this.quantidade = Double.valueOf(quantidade);
		this.preco = Double.valueOf(preco);
	}

	private void validar(String idItem, String quantidade, String preco) {
		validarId(idItem);
		validarQuantidade(quantidade);
		validarPreco(preco);
	}

	private Long idItem;
	private Double quantidade;
	private Double preco;

	public Double getPreco() {
		return preco;
	}

	public Double getQuantidade() {
		return quantidade;
	}
	
	private void validarId(String idItem) {
		// TODO Auto-generated method stub
		
	}
	
	private void validarQuantidade(String quantidade) {
		// TODO Auto-generated method stub
		
	}
	
	private void validarPreco(String preco) {
		// TODO Auto-generated method stub
		
	}
}
