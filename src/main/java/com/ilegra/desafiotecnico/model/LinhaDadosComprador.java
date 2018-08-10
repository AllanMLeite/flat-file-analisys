package com.ilegra.desafiotecnico.model;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.validator.ValidatorUtil;

public class LinhaDadosComprador extends Linha {
	public LinhaDadosComprador(String cnpj, String nome, String areaNegocio) throws DomainException {
		validarCnpj(cnpj);
		this.cnpj = cnpj;
		this.areaNegocio = areaNegocio;
		this.nome = nome;
	}

	private Integer idLinha = 2;

	private String cnpj;
	private String nome;
	private String areaNegocio;

	public Integer getIdLinha() {
		return idLinha;
	}

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
		if (!ValidatorUtil.isCnpjValido(cnpj))
			throw new DomainException("CNPJ invalido.");
	}
}
