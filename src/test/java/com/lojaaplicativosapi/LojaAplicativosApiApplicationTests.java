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
		Aplicativo aplicativo1 = Aplicativo.builder()
									.uuid(null)
									.nome("Aplicavivo 1")
									.preco(new BigDecimal("18.90"))
									.tipo(Tipo.ANDROID)
									.descricao("Descrição 1")
									.comentarios(new ArrayList<>())
								.build();
		
		Aplicativo appSalvo = aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		
		System.out.println("App Salvo: "+appSalvo);
		
		Aplicativo aplicativo1ComExecutavel = null;
		try {
			byte executavelApp1[] = Files.readAllBytes(Path.of("executativeis_teste/app1.apk"));
			
			System.out.println("idAppSalvo1: "+appSalvo.getUuid());
			aplicativo1ComExecutavel = aplicativoSerivice.localizarPeloId(appSalvo.getUuid());
			aplicativo1ComExecutavel.setExecutavel(executavelApp1);
			aplicativoSerivice.cadastrarOuAtualizar(aplicativo1ComExecutavel);
			System.out.println("App com executavel: "+aplicativo1ComExecutavel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Assertions.assertNotNull(appSalvo.getUuid());
		Assertions.assertNotNull(aplicativoSerivice.localizarPeloId(appSalvo.getUuid()).getExecutavel());
	}
	
}
