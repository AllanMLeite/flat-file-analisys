package com.ilegra.desafiotecnico.analise;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ilegra.desafiotecnico.config.TestConfig;
import com.ilegra.desafiotecnico.controller.AnaliseController;

import cucumber.api.DataTable;

public class PassosParaAnalisarDados extends TestConfig implements cucumber.api.java8.Pt {

	@Autowired
	AnaliseController analiseController;

	private static final String EXTENSAO_DAT = ".dat";

	public PassosParaAnalisarDados() {

		Path pathIn = Paths.get(System.getProperty("user.home") + "\\data\\in\\");
		Path pathOut = Paths.get(System.getProperty("user.home") + "\\data\\out\\");

		Dado("^que existam os diretorios \"([^\"]*)\" e \"([^\"]*)\"$", (String arg1, String arg2) -> {
			Files.createDirectories(pathIn);
			Files.createDirectories(pathOut);
		});

		Dado("^que exista um arquivo chamado \"([^\"]*)\" com as linhas abaixo$",
				(String nomeArquivoParaIncluir, DataTable dataTableLinhasDoArquivos) -> {
					List<String> linhas = dataTableLinhasDoArquivos.asList(String.class);
					System.out.println(linhas.get(0));
					System.out.println(pathIn.resolve(nomeArquivoParaIncluir));
					Files.write(pathIn.resolve(nomeArquivoParaIncluir), linhas);
				});

		Quando("^processar$", () -> {
			analiseController.processar();
		});

		Entao("^deve criar um arquivo chamado \"([^\"]*)\" com a linha \"([^\"]*)\"$",
				(String nomeArquivoCriado, String linha) -> {
					File file = Files.walk(pathOut).filter(Files::isRegularFile)
							.filter(x -> x.getFileName().endsWith(nomeArquivoCriado)).map(Path::toFile)
							.collect(Collectors.toList()).get(0);
					BufferedReader br = new BufferedReader(new FileReader(file));
					assertEquals(linha, br.readLine());
				});
	}
}
