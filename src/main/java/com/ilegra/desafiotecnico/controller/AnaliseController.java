package com.ilegra.desafiotecnico.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.ilegra.desafiotecnico.config.ArquivoConfig;
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

	@Scheduled(fixedDelay = 3600000)
	public void processar() throws IOException {

		criarDiretorios();

		List<File> arquivosValidosNoDiretorio = fileUtil.listarArquivosDoDiretorio(ArquivoConfig.PATH_ENTRADA,
				ArquivoConfig.EXTENSAO_DAT);

		arquivosValidosNoDiretorio.forEach(arquivo -> {

			List<Linha> linhasDoArquivoNormalizadas = normalizadorService.normalizarLinhas(arquivo);

			Estatistica estatistica = estatisticaService.analisarDados(linhasDoArquivoNormalizadas);

			gravarEstatistica(arquivo, estatistica);

			removerArquivo(arquivo);

		});
	}

	private void criarDiretorios() throws IOException {
		fileUtil.criarDiretorio(ArquivoConfig.PATH_ENTRADA);
		fileUtil.criarDiretorio(ArquivoConfig.PATH_SAIDA);
	}

	private void removerArquivo(File arquivo) {
		fileUtil.removerArquivo(arquivo);
	}

	private void gravarEstatistica(File arquivo, Estatistica estatistica) {
		fileUtil.gravarArquivo(
				ArquivoConfig.PATH_SAIDA.resolve(
						arquivo.getName().replace(ArquivoConfig.EXTENSAO_DAT, ArquivoConfig.EXTENSAO_DONE_DAT)),
				estatistica.toString());
	}
}
