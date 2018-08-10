package com.ilegra.desafiotecnico.validator.cpf;

import static org.junit.Assert.assertEquals;

import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.validator.CpfValidator;

public class PassosParaValidarCpf extends TestConfig implements cucumber.api.java8.Pt {

	private String cpf;
	private Boolean resultado;

	public PassosParaValidarCpf() {

		Dado("^que informei o cpf \"([^\"]*)\"$", (String cpf) -> {
			this.cpf = cpf;
		});

		Quando("^validar$", () -> {
			resultado = CpfValidator.isCpfValido(cpf);
		});

		Entao("^deve retornar \"([^\"]*)\"$", (Boolean resultadoEsperado) -> {
			assertEquals(resultadoEsperado, resultado);
		});
	}
}
