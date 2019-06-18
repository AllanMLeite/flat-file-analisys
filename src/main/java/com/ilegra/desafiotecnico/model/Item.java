package com.ilegra.desafiotecnico.model;

import com.ilegra.desafiotecnico.exception.DomainException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
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
}
