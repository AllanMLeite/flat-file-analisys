package com.ilegra.desafiotecnico.config;

import org.springframework.boot.test.context.SpringBootTest;

import com.ilegra.desafiotecnico.DesafioTecnicoApplication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {
		DesafioTecnicoApplication.class })
public class TestConfig {
}
