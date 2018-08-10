package com.ilegra.desafiotecnico.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Estatistica;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.enums.TipoDadoEnum;
import com.ilegra.desafiotecnico.service.EstatisticaService;
import com.ilegra.desafiotecnico.util.FileUtil;

@Controller
public class AnaliseController {

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private EstatisticaService estatisticaService;

	private static final Path PATH_ENTRADA = Paths.get(System.getProperty("user.home") + "\\data\\in\\");
	private static final Path PATH_SAIDA = Paths.get(System.getProperty("user.home") + "\\data\\out\\");

	private static final int POSICAO_INICIAL_ID_LINHA = 0;
	private static final int POSICAO_FINAL_ID_LINHA = 3;

	private static final String EXTENSAO_DAT = ".dat";

	public void processar() throws IOException {
		
		Files.createDirectories(PATH_ENTRADA);
		Files.createDirectories(PATH_SAIDA);

		List<File> arquivosValidosNoDiretorio = fileUtil.listarArquivosDoDiretorio(PATH_ENTRADA, EXTENSAO_DAT);

		arquivosValidosNoDiretorio.forEach(arquivo -> {

			List<Linha> linhasDoArquivoNormalizadas = normalizarLinhas(arquivo);

			Estatistica estatistica = estatisticaService.analisarDados(linhasDoArquivoNormalizadas);

			gravarArquivo(arquivo, estatistica);

		});
	}

	private void gravarArquivo(File arquivo, Estatistica estatistica) {
		fileUtil.gravarArquivo(PATH_SAIDA.resolve(arquivo.getName().replace(".dat", ".done.dat")),
				estatistica.toString());
	}

	private List<Linha> normalizarLinhas(File arquivo) {
		List<Linha> linhasDoArquivo = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(arquivo));
			for (String line; (line = br.readLine()) != null;) {
				try {
					Linha linha = converterLinha(line);
					linhasDoArquivo.add(linha);
				} catch (DomainException e) {
					System.out.println("Arquivo " + arquivo.getName() + " com erro: " + e.getMessage());
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao ler linha do arquivo do arquivo " + arquivo.getName());
		}

		return linhasDoArquivo;
	}

	private Linha converterLinha(String line) throws DomainException {
		return (Linha) TipoDadoEnum.buscarPeloId(line.substring(POSICAO_INICIAL_ID_LINHA, POSICAO_FINAL_ID_LINHA))
				.getConverter().converter(line.split(";"));
	}
}
