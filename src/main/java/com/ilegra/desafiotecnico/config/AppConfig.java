package com.ilegra.desafiotecnico.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class AppConfig {

	@Getter
	@Value("${extension.in}")
	private String inputExtension;
	
	@Getter
	@Value("${extension.out}")
	private String outputExtension;
	
	@Value("${path.in}")
	private String pathIn;
	
	@Value("${path.out}")
	private String pathOut;
	
	public Path getPathEntrada() {
		return Paths.get(System.getProperty("user.home") + pathIn);
	}
	
	public Path getPathSaida() {
		return Paths.get(System.getProperty("user.home") + pathOut);
	}
}
