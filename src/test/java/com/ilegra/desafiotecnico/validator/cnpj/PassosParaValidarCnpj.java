package com.ilegra.desafiotecnico.validator.cnpj;

import static org.junit.Assert.assertEquals;

import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.validator.CnpjValidator;

public class PassosParaValidarCnpj extends TestConfig implements cucumber.api.java8.Pt {

	private String cnpj;
	private Boolean resultado;

	public PassosParaValidarCnpj() {

		Dado("^que informei o cnpj \"([^\"]*)\"$", (String cnpj) -> {
			this.cnpj = cnpj;
		});

		Quando("^validar$", () -> {
			resultado = CnpjValidator.isCnpjValido(cnpj);
		});

		Entao("^deve retornar \"([^\"]*)\"$", (Boolean resultadoEsperado) -> {
			assertEquals(resultadoEsperado, resultado);
		});
	}
}
