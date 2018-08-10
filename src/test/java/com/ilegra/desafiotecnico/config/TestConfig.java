package com.ilegra.desafiotecnico.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.ilegra.desafiotecnico.DesafioTecnicoApplication;

@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {
		DesafioTecnicoApplication.class })
public class TestConfig {
}
