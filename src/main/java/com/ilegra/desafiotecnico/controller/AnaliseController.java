package com.ilegra.desafiotecnico.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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

import lombok.extern.slf4j.Slf4j;

@Controller
@EnableScheduling
@Slf4j
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
		
		log.info("Arquivos encontrados: {} ", arquivosValidosNoDiretorio);

		arquivosValidosNoDiretorio.forEach(arquivo -> {
			
			log.info("Processando arquivo {}", arquivo.getName());

			List<Linha> linhasDoArquivoNormalizadas = normalizadorService.normalizarLinhas(arquivo);
			
			log.info("Arquivo {} normalizado: {}", arquivo.getName(), linhasDoArquivoNormalizadas);

			Estatistica estatistica = estatisticaService.analisarDados(linhasDoArquivoNormalizadas);
			
			log.info("Estatistica gerada do arquivo {}: {}", arquivo.getName(), estatistica);

			gravarEstatistica(arquivo, estatistica);

			fileUtil.removerArquivo(arquivo);
			
			log.info("Removido arquivo de entrada: {}", arquivo.getName());
		});
	}

	private void gravarEstatistica(File arquivo, Estatistica estatistica) {
		
		String pathAsText = arquivo.getName().replace(config.getInputExtension(), config.getOutputExtension());
		
		Path path = config.getPathSaida().resolve(pathAsText);
		
		fileUtil.gravarArquivo(path, estatistica.toString());
		
		log.info("Estatistica armazenada no endereco {}", path);
	}
}
