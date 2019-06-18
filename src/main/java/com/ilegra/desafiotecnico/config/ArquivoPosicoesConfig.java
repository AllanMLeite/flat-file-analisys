package com.ilegra.desafiotecnico.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class ArquivoPosicoesConfig {

	@Value("${posicao.vendedor.cpf}")
	private Integer posicaoVendedorCpf;
	
	@Value("${posicao.vendedor.nome}")
	private Integer posicaoVendedorNome;
	
	@Value("${posicao.vendedor.salario}")
	private Integer posicaoVendedorSalario;

	@Value("${posicao.comprador.cnpj}")
	private Integer posicaoCompradorCnpj;
	
	@Value("${posicao.comprador.nome}")
	private Integer posicaoCompradorNome;
	
	@Value("${posicao.comprador.area-negocio}")
	private Integer posicaoCompradorAreaNegocio;

	@Value("${posicao.venda.id}")
	private Integer posicaoVendaId;
	
	@Value("${posicao.venda.itens}")
	private Integer posicaoVendaItens;
	
	@Value("${posicao.venda.vendedor}")
	private Integer posicaoVendaVendedor;

	@Value("${posicao.venda.item.id}")
	private Integer posicaoVendaItemId;
	
	@Value("${posicao.venda.item.quantidade}")
	private Integer posicaoVendaItemQuantidade;
	
	@Value("${posicao.venda.item.preco}")
	private Integer posicaoVendaItemPreco;
}
