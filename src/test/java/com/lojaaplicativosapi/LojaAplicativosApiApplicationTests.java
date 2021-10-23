package com.lojaaplicativosapi;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lojaaplicativosapi.dto.AvaliacaoAplicativoDTO;
import com.lojaaplicativosapi.dto.ComentarioDTO;
import com.lojaaplicativosapi.enumtype.Tipo;
import com.lojaaplicativosapi.model.aplicativo.Aplicativo;
import com.lojaaplicativosapi.service.AplicativoSerivice;
import com.lojaaplicativosapi.util.IdUtilSingleton;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class LojaAplicativosApiApplicationTests {
	
	@Autowired
	private AplicativoSerivice aplicativoSerivice;
	
	@Test
	@Order(1)
	void criarAplicativoTeste() {
		Aplicativo aplicativo1 = Aplicativo
								.builder()
									.uuid(null)
									.nome("Aplicavivo 1")
									.preco(new BigDecimal("18.90"))
									.tipo(Tipo.ANDROID)
									.descricao("Descrição 1")
									.comentarios(new ArrayList<>())
								.build();
		
		Aplicativo aplicativo2 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 2")
					.preco(new BigDecimal("9.90"))
					.tipo(Tipo.WINDOWS)
					.descricao("Descrição 2")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo3 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 3")
					.preco(new BigDecimal("32.38"))
					.tipo(Tipo.IOS)
					.descricao("Descrição 3")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo4 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 4")
					.preco(new BigDecimal("48.90"))
					.tipo(Tipo.LINUX)
					.avaliacaoGeral(4) // 4 estrelas
					.descricao("Descrição 4")
					.comentarios(new ArrayList<>())
					.build();
		
		Aplicativo aplicativo5 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 5")
					.preco(new BigDecimal("78.12"))
					.tipo(Tipo.ANDROID)
					.descricao("Descrição 5")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo6 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 6")
					.preco(new BigDecimal("43.87"))
					.tipo(Tipo.WINDOWS)
					.descricao("Descrição 6")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo7 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 7")
					.preco(new BigDecimal("27.36"))
					.tipo(Tipo.IOS)
					.descricao("Descrição 7")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo8 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 8")
					.preco(new BigDecimal("1890.20"))
					.tipo(Tipo.MAC_OS)
					.descricao("Descrição 8")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo9 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 9")
					.preco(new BigDecimal("512.99"))
					.tipo(Tipo.MAC_OS)
					.descricao("Descrição 9")
					.comentarios(new ArrayList<>())
				.build();
		
		Aplicativo aplicativo10 = Aplicativo
				.builder()
					.uuid(null)
					.nome("Aplicavivo 10")
					.preco(new BigDecimal("90.78"))
					.tipo(Tipo.ANDROID)
					.descricao("Descrição 10")
					.comentarios(new ArrayList<>())
				.build();
		
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo2);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo3);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo4);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo5);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo6);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo7);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo8);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo9);
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo10);
		
		Aplicativo a = aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		Aplicativo a2 = aplicativoSerivice.cadastrarOuAtualizar(aplicativo2);
		Aplicativo a4 = aplicativoSerivice.cadastrarOuAtualizar(aplicativo4);
		Aplicativo a9 = aplicativoSerivice.cadastrarOuAtualizar(aplicativo9);
		
		IdUtilSingleton.getInstancia().setUuid(a.getUuid());
		IdUtilSingleton.getInstancia().setUuidWindows(a2.getUuid());
		IdUtilSingleton.getInstancia().setUuidLinux(a4.getUuid());
		IdUtilSingleton.getInstancia().setUuidMac(a9.getUuid());
		
		Assertions.assertNotNull(a.getUuid());
	}
	
	@Test
	@Order(2)
	void fazerUploadExecutavelTeste() {
		aplicativoSerivice.upload("upload_teste/app1.apk", IdUtilSingleton.getInstancia().getUuid());
		
		System.out.println("UUID: "+IdUtilSingleton.getInstancia().getUuid());
		Assertions.assertNotNull(aplicativoSerivice.localizarPeloId(IdUtilSingleton.getInstancia().getUuid()).getExecutavel());
	}
	
	@Test
	@Order(3)
	void fazerDownloadExecutavelTeste() {
		Assertions.assertTrue(aplicativoSerivice.download("download-teste", "app1.apk", IdUtilSingleton.getInstancia().getUuid()));
	}
	
	@Test
	@Order(4)
	void deleteArquivoBaixadoTeste() {
		try {
			Assertions.assertTrue(Files.deleteIfExists(Path.of("download-teste/app1.apk")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	void encontraPeloNomeTipoTeste() {
		Aplicativo aplicativo2 = Aplicativo
				.builder()
					.uuid(IdUtilSingleton.getInstancia().getUuidWindows())
					.nome("Aplicavivo 2")
					.preco(new BigDecimal("9.90"))
					.tipo(Tipo.WINDOWS)
					.descricao("Descrição 2")
					.comentarios(new ArrayList<>())
				.build();
		
		Assertions.assertEquals(aplicativo2, aplicativoSerivice.encontrar("Aplicavivo 2", Tipo.WINDOWS));
	}
	
	@Test
	@Order(6)
	void encontrarPeloMenorPrecoTipoTeste() {
		Aplicativo aplicativo9 = Aplicativo
				.builder()
					.uuid(IdUtilSingleton.getInstancia().getUuidMac())
					.nome("Aplicavivo 9")
					.preco(new BigDecimal("512.99"))
					.tipo(Tipo.MAC_OS)
					.descricao("Descrição 9")
					.comentarios(new ArrayList<>())
				.build();
		
		Assertions.assertEquals(aplicativo9, aplicativoSerivice.encontrarPeloMenorPreco(Tipo.MAC_OS));
	}
	
	@Test 
	@Order(7)
	void fazerUmaAvaliacaoIndividualNoAplicativoTeste() {
		AvaliacaoAplicativoDTO avaliacaoAplicativo4 = new AvaliacaoAplicativoDTO();
		avaliacaoAplicativo4.setUuid(IdUtilSingleton.getInstancia().getUuidLinux());
		avaliacaoAplicativo4.setAvaliacaoIndividual(5);
		
		Aplicativo aplicativo4 = Aplicativo
				.builder()
					.uuid(IdUtilSingleton.getInstancia().getUuidLinux())
					.nome("Aplicavivo 4")
					.preco(new BigDecimal("48.90"))
					.tipo(Tipo.LINUX) // * = estrelas
					.avaliacaoGeral(4) // 4 estrelas -> (avaliação geral 4* somada com a avaliação individual 5* divido por 2 = 9, 9/2=4.5 arredondando para 4 estrelas)
					.avaliacaoIndividual((byte) 5) // 5 estrelas
					.descricao("Descrição 4")
					.comentarios(new ArrayList<>())
				.build();
		
		Assertions.assertEquals(aplicativo4, aplicativoSerivice.fazerAvaliacaoIndividual(avaliacaoAplicativo4));
	}
	
	@Test 
	@Order(8)
	void fazerUmComentarioTeste() {
		ComentarioDTO comentarioDTO = new ComentarioDTO(IdUtilSingleton.getInstancia().getUuidMac(), "Autor 1", "Conteúdo 1");
		
		Assertions.assertEquals(1, aplicativoSerivice.fazerComentario(comentarioDTO).getComentarios().size());
	}
	
	@Test 
	@Order(9)
	void encontrarAplicativoPorPaginasTeste() {
		Integer quantidadeItensPagina1 = aplicativoSerivice.encontrarAplicativos(0, 5).size(); // Página 1 -> 5 itens
		Integer quantidadeItensPagina2 = aplicativoSerivice.encontrarAplicativos(1, 5).size(); // Página 2 -> 5 itens
		Integer somaTotalDeItensPaginas = quantidadeItensPagina1 + quantidadeItensPagina2; // Soma de itens das duas páginas igual a 10
		
		Assertions.assertTrue(somaTotalDeItensPaginas == 10);
	}
	
}
