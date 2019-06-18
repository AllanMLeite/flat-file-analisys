package com.ilegra.desafiotecnico.analise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.AppConfig;
import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.controller.AnaliseController;

import cucumber.api.DataTable;

public class PassosParaAnalisarDados extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	private AnaliseController analiseController;
	
	@Autowired
	private AppConfig config;

	public PassosParaAnalisarDados() {

		Dado("^que existam os diretorios \"([^\"]*)\" e \"([^\"]*)\"$", (String arg1, String arg2) -> {			
			Files.createDirectories(config.getPathEntrada());
			Files.createDirectories(config.getPathSaida());
		});

		Dado("^que exista um arquivo chamado \"([^\"]*)\" com as linhas abaixo$",
				(String nomeArquivoParaIncluir, DataTable dataTableLinhasDoArquivos) -> {
			List<String> linhas = dataTableLinhasDoArquivos.asList(String.class);
			Files.write(config.getPathEntrada().resolve(nomeArquivoParaIncluir), linhas);
		});

		Quando("^processar$", () -> {
			analiseController.processar();
		});

		Entao("^deve criar um arquivo chamado \"([^\"]*)\" com a linha \"([^\"]*)\"$",
				(String nomeArquivoCriado, String linha) -> {
			File file = Files.walk(config.getPathSaida()).filter(Files::isRegularFile)
					.filter(x -> x.getFileName().endsWith(nomeArquivoCriado)).map(Path::toFile)
					.collect(Collectors.toList()).get(0);
			BufferedReader br = new BufferedReader(new FileReader(file));
			assertEquals(linha, br.readLine());
		});

		Entao("^deve remover o arquivo \"([^\"]*)\"$", (String nomeArquivo) -> {
			List<File> files = Files.walk(config.getPathEntrada()).filter(Files::isRegularFile)
					.filter(x -> x.getFileName().endsWith(nomeArquivo)).map(Path::toFile).collect(Collectors.toList());
			assertTrue(files.isEmpty());
		});

	}
}
