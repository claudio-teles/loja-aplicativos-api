package com.lojaaplicativosapi.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lojaaplicativosapi.dto.AvaliacaoAplicativoDTO;
import com.lojaaplicativosapi.dto.ComentarioDTO;
import com.lojaaplicativosapi.enumtype.Tipo;
import com.lojaaplicativosapi.model.aplicativo.Aplicativo;
import com.lojaaplicativosapi.model.comentario.Comentario;
import com.lojaaplicativosapi.repository.AplicativoRepository;
import com.lojaaplicativosapi.repository.ComentarioRepository;

@Service
public class AplicativoSerivice {
	
	@Autowired
	private AplicativoRepository aplicativoRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	private BigDecimal menorValor;
	private Aplicativo aplicativo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Aplicativo.class);
	
	public Aplicativo cadastrarOuAtualizar(Aplicativo aplicativo) {
		if (aplicativo != null) {
			return aplicativoRepository.save(aplicativo);
		}
		return null;
	}
	
	public Aplicativo localizarPeloId(UUID id) {
		return aplicativoRepository.getById(id);
	}
	
	public Aplicativo encontrar(String nome, Tipo tipo) {
		return aplicativoRepository.findByNomeAndTipo(nome, tipo);
	}
	
	public Aplicativo encontrarPeloMenorPreco(Tipo tipo) {
		LOGGER.info("\n =========================================  Lista Pelo Tipo ==================================================================");
		LOGGER.info("\n Lista Completa De Aplicativos Por Tipo: ", aplicativoRepository.findAllByTipo(tipo));
		LOGGER.info("\n =========================================  Lista Pelo Tipo ==================================================================");
		
		menorValor = aplicativoRepository.findAllByTipo(tipo).get(0).getPreco();
		aplicativo = new Aplicativo();
		
		LOGGER.info("\b =========================================  Menor Preço ==================================================================");
		aplicativoRepository.findAllByTipo(tipo).stream()
												.filter(app -> (app.getPreco().compareTo(menorValor)) == -1)
												.forEach(
															aplicacao -> {
																menorValor = aplicacao.getPreco();
																aplicativo = aplicacao;
																LOGGER.info("\n Aplicação Com Menor Preço Encontrado Na Lista: "+aplicacao);
															}
														);
		LOGGER.info("\n =========================================  Menor Preço ==================================================================");
		
		return aplicativo;
	}
	
	public void upload(String path, UUID uuid) {
		try {
			byte executavelApp1[] = Files.readAllBytes(Path.of(path));
			
			Aplicativo aplicativo1ComExecutavel = localizarPeloId(uuid);
			aplicativo1ComExecutavel.setExecutavel(executavelApp1);
			cadastrarOuAtualizar(aplicativo1ComExecutavel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean download(String diretorio, String nomeDoArquivo, UUID uuid) {
		Boolean igual = null;
		try {
			Long quantidadeInicialDeArquvos = Files.list(Path.of(diretorio)).count(); // quantidade 0
			
			Aplicativo aplicativo1ComExecutavel = localizarPeloId(uuid);
			
			Files.write(Path.of(diretorio+"/"+nomeDoArquivo), aplicativo1ComExecutavel.getExecutavel(), StandardOpenOption.CREATE);
			
			Long quantidadeFinalDeArquvos = Files.list(Path.of("download-teste")).count(); // quantidade 1
			
			igual = (quantidadeInicialDeArquvos != quantidadeFinalDeArquvos); // true ou false
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return igual;
	}
	
	public Aplicativo fazerAvaliacaoIndividual(AvaliacaoAplicativoDTO avaliacaoAplicativoDTO) {
		if (avaliacaoAplicativoDTO != null) {
			Aplicativo app = localizarPeloId(avaliacaoAplicativoDTO.getUuid());
			
			app.setAvaliacaoIndividual(avaliacaoAplicativoDTO.getAvaliacaoIndividual());
			byte ag = (byte) Math.round(((app.getAvaliacaoGeral() + avaliacaoAplicativoDTO.getAvaliacaoIndividual()) / 2));
			
			app.setAvaliacaoGeral(ag);
			return cadastrarOuAtualizar(app);
		}
		return null;
	}
	
	public Aplicativo fazerComentario(ComentarioDTO comentarioDTO) {
		if (comentarioDTO != null) {
			Aplicativo app = localizarPeloId(comentarioDTO.getUuidAplicativo());
			
			Comentario c = new Comentario(null, comentarioDTO.getAutor(), comentarioDTO.getConteudo(),
					LocalDateTime.now());
			Comentario comentario = comentarioRepository.save(c);
			System.err.println("====================> "+comentario);
			
			app.getComentarios().add(comentario);
			return cadastrarOuAtualizar(app);
		}
		return null;
	}
	
	public List<Aplicativo> encontrarAplicativos(Integer numeroDePaginas, Integer quantidadeItensPagina) {
		return aplicativoRepository.findAll(PageRequest.of(numeroDePaginas, quantidadeItensPagina, Sort.by("nome"))).getContent();
	}

}
