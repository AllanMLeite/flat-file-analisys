package com.ilegra.desafiotecnico.converter;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;

public interface LinhaDadosConverter {

	public abstract Linha converter(String[] dados) throws DomainException;
}
