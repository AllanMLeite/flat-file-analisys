package com.ilegra.desafiotecnico.analise.converter.vendedor;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import com.ilegra.desafiotecnico.DesafioTecnicoApplication;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@ContextConfiguration(loader = SpringBootContextLoader.class, classes = DesafioTecnicoApplication.class)
@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\cucumber\\analise\\converter\\vendedor\\US003_ConverterDadosVendedor.feature", strict = true)
public class ConverterDadosVendedorTest {
}
