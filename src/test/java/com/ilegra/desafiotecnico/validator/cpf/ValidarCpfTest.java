package com.ilegra.desafiotecnico.validator.cpf;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import com.ilegra.desafiotecnico.DesafioTecnicoApplication;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@ContextConfiguration(loader = SpringBootContextLoader.class, classes = DesafioTecnicoApplication.class)
@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\cucumber\\validador\\cpf\\ValidarCpf.feature", strict = true)
public class ValidarCpfTest {
}
