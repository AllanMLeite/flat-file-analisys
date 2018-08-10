package com.ilegra.desafiotecnico.model;

import com.ilegra.desafiotecnico.exception.DomainException;

public class Item {

	public Item(String idItem, String quantidade, String preco) throws DomainException {
		super();
		validar(quantidade, preco);
		this.idItem = Long.valueOf(idItem);
		this.quantidade = Double.valueOf(quantidade);
		this.preco = Double.valueOf(preco);
	}

	private void validar(String quantidade, String preco) throws DomainException {
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

	private void validarQuantidade(String quantidade) throws DomainException {
		try {
			Double.parseDouble(quantidade);
		} catch (NumberFormatException e) {
			throw new DomainException("Quantidade invalida.");
		}
	}

	private void validarPreco(String preco) throws DomainException {
		try {
			Double.parseDouble(preco);
		} catch (NumberFormatException e) {
			throw new DomainException("Preco invalido.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (idItem == null) {
			if (other.idItem != null)
				return false;
		} else if (!idItem.equals(other.idItem))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}		
}
