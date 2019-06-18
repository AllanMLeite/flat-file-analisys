package com.ilegra.desafiotecnico.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ilegra.desafiotecnico.config.AppConfig;

@Component
public class FileUtil {

	@Autowired
	private AppConfig config;
	
	public List<File> listarArquivosDoDiretorio(Path path, String extensao) throws IOException {
		return Files.walk(path).filter(Files::isRegularFile).filter(x -> x.toString().endsWith(extensao))
				.map(Path::toFile).collect(Collectors.toList());
	}

	public void gravarArquivo(Path path, String dados) {
		try {
			Files.write(path, Arrays.asList(dados));
		} catch (IOException e) {
			System.out.println("Erro ao gravar arquivo: " + path);
		}
	}

	public void removerArquivo(File arquivo) {
		arquivo.delete();		
	}
	
	public void criarDiretorios() throws IOException {
		criarDiretorio(config.getPathEntrada());
		criarDiretorio(config.getPathSaida());
	}
	
	private void criarDiretorio(Path path) throws IOException {
		Files.createDirectories(path);
	}
}
