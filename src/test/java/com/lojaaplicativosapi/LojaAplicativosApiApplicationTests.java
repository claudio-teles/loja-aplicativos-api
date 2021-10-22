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

import com.lojaaplicativosapi.enumtype.Tipo;
import com.lojaaplicativosapi.model.aplicativo.Aplicativo;
import com.lojaaplicativosapi.servico.AplicativoSerivice;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class LojaAplicativosApiApplicationTests {
	
	@Autowired
	private AplicativoSerivice aplicativoSerivice;
	
	@Test
	@Order(1)
	void criarAplicativo() {
		Aplicativo aplicativo1 = Aplicativo
								.builder()
									.id(null)
									.nome("Aplicavivo 1")
									.preco(new BigDecimal("18.90"))
									.tipo(Tipo.ANDROID)
									.descricao("Descrição 1")
									.comentarios(new ArrayList<>())
								.build();
		
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		
		Aplicativo a = aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		
		Assertions.assertNotNull(a.getId());
	}
	
	@Test
	@Order(2)
	void fazerUploadExecutavel() {
		try {
			byte executavelApp1[] = Files.readAllBytes(Path.of("executaveis_teste/app1.apk"));
			
			Aplicativo aplicativo1ComExecutavel = aplicativoSerivice.localizarPeloId(1L);
			aplicativo1ComExecutavel.setExecutavel(executavelApp1);
			aplicativoSerivice.cadastrarOuAtualizar(aplicativo1ComExecutavel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Assertions.assertNotNull(aplicativoSerivice.localizarPeloId(1L).getExecutavel());
	}
	
}
