package com.lojaaplicativosapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojaaplicativosapi.dto.AvaliacaoAplicativoDTO;
import com.lojaaplicativosapi.dto.ComentarioDTO;
import com.lojaaplicativosapi.enumtype.Tipo;
import com.lojaaplicativosapi.model.aplicativo.Aplicativo;
import com.lojaaplicativosapi.service.AplicativoSerivice;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class AplicatvoController {
	
	@Autowired
	private AplicativoSerivice aplicativoSerivice;
	
	@PostMapping("/aplicativo")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar aplicativo")
	public Aplicativo cadastrar(@RequestBody Aplicativo aplicativo) {
		return aplicativoSerivice.cadastrarOuAtualizar(aplicativo);
	}
	
	@GetMapping("/aplicativos")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Lista todos os aplicativos em páginas diferentes")
	public List<Aplicativo> listarAplicativosPorPaginas(@RequestParam(name = "numeroPaginas") Integer numeroPaginas, @RequestParam(name = "quantidadeDeItensPagina") Integer quantidadeDeItensPagina) {
		return aplicativoSerivice.encontrarAplicativos(numeroPaginas, quantidadeDeItensPagina);
	}
	
	@GetMapping("/aplicativo/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Encontra um aplicativo pelo uuid")
	public Aplicativo encontrarPeloId(@PathVariable("uuid") UUID uuid) {
		return aplicativoSerivice.localizarPeloId(uuid);
	}
	
	@GetMapping("/aplicativo/nome_e_tipo")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Encontra um aplicativo pelo nome e pelo o tipo")
	public Aplicativo encontrarPeloNomeTipo(@RequestParam(name = "nome") String nome, @RequestParam(name = "tipo") Tipo tipo) {
		return aplicativoSerivice.encontrar(nome, tipo);
	}
	
	@GetMapping("/aplicativo/menor_preco_tipo")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Encontra um aplicativo pelo menor preço e o seu tipo")
	public Aplicativo encontrarPeloMenorPrecoTipo(@RequestParam(name = "tipo") Tipo tipo) {
		return aplicativoSerivice.encontrarPeloMenorPreco(tipo);
	}
	
	@PutMapping("/aplicativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Faz o upload de um aplicativo informando o local do diretório(path) e o uuid")
	public void fazerUpload(@RequestParam(name = "path") String path, @RequestParam(name = "uuid") UUID uuid) {
		aplicativoSerivice.upload(path, uuid);
	}
	
	@GetMapping("/aplicativo")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Faz o download de um aplicativo informando o local do diretório, o nome do arquivo e o uuid")
	public Boolean fazerDownload(@RequestParam(name = "diretorio") String diretorio, @RequestParam(name = "nomeDoArquivo") String nomeDoArquivo, @RequestParam(name = "uuid") UUID uuid) {
		return fazerDownload(diretorio, nomeDoArquivo, uuid);
	}
	
	@PutMapping("/aplicativo/avaliacao")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@ApiOperation(value = "Faz um avaliação de um aplicativo informando o uuid e a quantidade de estrelas em que a nota vai de 1 a 5 para avaliação")
	public Aplicativo fazerAvaliacaoIndividual(@RequestBody AvaliacaoAplicativoDTO avaliacaoAplicativoDTO) {
		return aplicativoSerivice.fazerAvaliacaoIndividual(avaliacaoAplicativoDTO);
	}
	
	@PutMapping("/aplicativo/comentario")
	@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
	@ApiOperation(value = "Salva um comentário no cadastro de um aplicativo")
	public Aplicativo fazerComentario(@RequestBody ComentarioDTO comentarioDTO) {
		return aplicativoSerivice.fazerComentario(comentarioDTO);
	}

}
