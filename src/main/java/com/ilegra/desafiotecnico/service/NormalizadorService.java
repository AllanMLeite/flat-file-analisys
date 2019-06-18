package com.ilegra.desafiotecnico.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilegra.desafiotecnico.converter.LinhaDadosConverter;
import com.ilegra.desafiotecnico.converter.LinhaDadosConverterFactory;
import com.ilegra.desafiotecnico.exception.DomainException;
import com.ilegra.desafiotecnico.model.Linha;
import com.ilegra.desafiotecnico.model.enums.TipoDadoEnum;

@Service
public class NormalizadorService {

	private static final int POSICAO_INICIAL_ID_LINHA = 0;
	private static final int POSICAO_FINAL_ID_LINHA = 3;
	
	@Autowired
	private LinhaDadosConverterFactory converterFactory;

	public List<Linha> normalizarLinhas(File arquivo) {
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
		TipoDadoEnum tipoDadoEnum = TipoDadoEnum.buscarPeloId(line.substring(POSICAO_INICIAL_ID_LINHA, POSICAO_FINAL_ID_LINHA));
		LinhaDadosConverter linhaDadosConverter = converterFactory.getInstance(tipoDadoEnum.getConverterId());
		return (Linha) linhaDadosConverter.converter(line.split(";"));
	}
}
