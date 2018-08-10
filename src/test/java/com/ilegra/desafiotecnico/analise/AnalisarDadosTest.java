package com.ilegra.desafiotecnico.analise;

import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;

import com.ilegra.desafiotecnico.DesafioTecnicoApplication;
import cucumber.api.junit.Cucumber;

import cucumber.api.CucumberOptions;

@ContextConfiguration(loader = SpringBootContextLoader.class, classes = DesafioTecnicoApplication.class)
@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\cucumber\\analise\\US001_AnalisarDados.feature", strict = true)
public class AnalisarDadosTest {
}
