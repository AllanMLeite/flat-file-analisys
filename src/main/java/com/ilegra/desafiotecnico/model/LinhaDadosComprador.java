package com.ilegra.desafiotecnico.model;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.validator.CnpjValidator;

public class LinhaDadosComprador extends Linha {
	public LinhaDadosComprador(String cnpj, String nome, String areaNegocio) throws DomainException {
		super();
		validarCnpj(cnpj);
		this.cnpj = cnpj;
		this.areaNegocio = areaNegocio;
		this.nome = nome;
	}

	private String cnpj;
	private String nome;
	private String areaNegocio;

	public String getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}

	public String getAreaNegocio() {
		return areaNegocio;
	}

	private void validarCnpj(String cnpj) throws DomainException {
		if (!CnpjValidator.isCnpjValido(cnpj))
			throw new DomainException("CNPJ invalido.");
	}
}
