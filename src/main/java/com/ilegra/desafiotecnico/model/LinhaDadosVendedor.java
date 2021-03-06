package com.ilegra.desafiotecnico.model;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.validator.CpfValidator;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LinhaDadosVendedor extends Linha {

	public LinhaDadosVendedor(String cpf, String nome, String salario) throws DomainException {
		super();
		validarCPF(cpf);
		validarSalario(salario);
		this.cpf = cpf;
		this.nome = nome;
		this.salario = Double.parseDouble(salario);
	}

	private String cpf;
	private String nome;
	private Double salario;

	private void validarCPF(String cpf) throws DomainException {
		if (!CpfValidator.isCpfValido(cpf))
			throw new DomainException("CPF invalido.");
	}

	private void validarSalario(String salario) throws DomainException {
		try {
			Double.parseDouble(salario);
		} catch (NumberFormatException e) {
			throw new DomainException("Salario invalido.");
		}
	}
}
