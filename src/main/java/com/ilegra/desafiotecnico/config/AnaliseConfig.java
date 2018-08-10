package com.ilegra.desafiotecnico.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AnaliseConfig {

	public static final Path PATH_ENTRADA = Paths.get(System.getProperty("user.home") + "\\data\\in\\");
	public static final Path PATH_SAIDA = Paths.get(System.getProperty("user.home") + "\\data\\out\\");

	public static final String EXTENSAO_DAT = ".dat";
	public static final String EXTENSAO_DONE_DAT = ".done.dat";
}
