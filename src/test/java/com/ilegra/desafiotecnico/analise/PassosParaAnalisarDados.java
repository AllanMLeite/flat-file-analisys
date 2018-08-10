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

import com.ilegra.desafiotecnico.config.ArquivoConfig;
import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.controller.AnaliseController;

import cucumber.api.DataTable;

public class PassosParaAnalisarDados extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	AnaliseController analiseController;

	public PassosParaAnalisarDados() {

		Dado("^que existam os diretorios \"([^\"]*)\" e \"([^\"]*)\"$", (String arg1, String arg2) -> {			
			Files.createDirectories(ArquivoConfig.PATH_ENTRADA);
			Files.createDirectories(ArquivoConfig.PATH_SAIDA);
		});

		Dado("^que exista um arquivo chamado \"([^\"]*)\" com as linhas abaixo$",
				(String nomeArquivoParaIncluir, DataTable dataTableLinhasDoArquivos) -> {
					List<String> linhas = dataTableLinhasDoArquivos.asList(String.class);
					Files.write(ArquivoConfig.PATH_ENTRADA.resolve(nomeArquivoParaIncluir), linhas);
				});

		Quando("^processar$", () -> {
			analiseController.processar();
		});

		Entao("^deve criar um arquivo chamado \"([^\"]*)\" com a linha \"([^\"]*)\"$",
				(String nomeArquivoCriado, String linha) -> {
					File file = Files.walk(ArquivoConfig.PATH_SAIDA).filter(Files::isRegularFile)
							.filter(x -> x.getFileName().endsWith(nomeArquivoCriado)).map(Path::toFile)
							.collect(Collectors.toList()).get(0);
					BufferedReader br = new BufferedReader(new FileReader(file));
					assertEquals(linha, br.readLine());
				});

		Entao("^deve remover o arquivo \"([^\"]*)\"$", (String nomeArquivo) -> {
			List<File> files = Files.walk(ArquivoConfig.PATH_ENTRADA).filter(Files::isRegularFile)
					.filter(x -> x.getFileName().endsWith(nomeArquivo)).map(Path::toFile).collect(Collectors.toList());
			assertTrue(files.isEmpty());
		});

	}
}
