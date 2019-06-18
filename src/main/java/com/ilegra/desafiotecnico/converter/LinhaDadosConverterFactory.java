package com.ilegra.desafiotecnico.converter;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class LinhaDadosConverterFactory {

	@Resource
	private Map<String, LinhaDadosConverter> converters;

	public LinhaDadosConverter getInstance(String key) {
		return converters.get(key);
	}
}
