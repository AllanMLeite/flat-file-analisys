package com.ilegra.desafiotecnico.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.ilegra.desafiotecnico.config.AppConfig;
import com.ilegra.desafiotecnico.model.Estatistica;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.service.EstatisticaService;
import com.ilegra.desafiotecnico.service.NormalizadorService;
import com.ilegra.desafiotecnico.util.FileUtil;

@Controller
@EnableScheduling
public class AnaliseController {

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private EstatisticaService estatisticaService;

	@Autowired
	private NormalizadorService normalizadorService;
	
	@Autowired
	private AppConfig config;

	@Scheduled(fixedDelay = 3600000)
	public void processar() throws IOException {

		fileUtil.criarDiretorios();

		List<File> arquivosValidosNoDiretorio = fileUtil.listarArquivosDoDiretorio(config.getPathEntrada(),
				config.getInputExtension());

		arquivosValidosNoDiretorio.forEach(arquivo -> {

			List<Linha> linhasDoArquivoNormalizadas = normalizadorService.normalizarLinhas(arquivo);

			Estatistica estatistica = estatisticaService.analisarDados(linhasDoArquivoNormalizadas);

			gravarEstatistica(arquivo, estatistica);

			removerArquivo(arquivo);

		});
	}

	private void removerArquivo(File arquivo) {
		fileUtil.removerArquivo(arquivo);
	}

	private void gravarEstatistica(File arquivo, Estatistica estatistica) {
		fileUtil.gravarArquivo(
				config.getPathSaida().resolve(
						arquivo.getName().replace(config.getInputExtension(), config.getOutputExtension())),
				estatistica.toString());
	}
}
