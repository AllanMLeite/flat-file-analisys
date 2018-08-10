package com.ilegra.desafiotecnico.model;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.validator.ValidaCPF;

public class LinhaDadosVendedor extends Linha {

	public LinhaDadosVendedor(String cpf, String nome, String salario) throws DomainException {
		validarCPF(cpf);
		validarSalario(salario);
		this.cpf = cpf;
		this.nome = nome;
		this.salario = Double.parseDouble(salario);
	}

	private Integer idLinha = 1;

	private String cpf;
	private String nome;
	private Double salario;

	public Integer getIdLinha() {
		return idLinha;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public Double getSalario() {
		return salario;
	}

	private void validarCPF(String cpf) throws DomainException {
		if (!ValidaCPF.isValido(cpf))
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
