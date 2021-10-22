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
import com.lojaaplicativosapi.util.IdUtilSingleton;

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
									.uuid(null)
									.nome("Aplicavivo 1")
									.preco(new BigDecimal("18.90"))
									.tipo(Tipo.ANDROID)
									.descricao("Descrição 1")
									.comentarios(new ArrayList<>())
								.build();
		
		aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		
		Aplicativo a = aplicativoSerivice.cadastrarOuAtualizar(aplicativo1);
		IdUtilSingleton.getInstancia().setUuid(a.getUuid());
		
		Assertions.assertNotNull(a.getUuid());
	}
	
	@Test
	@Order(2)
	void fazerUploadExecutavel() {
		aplicativoSerivice.upload("upload_teste/app1.apk", IdUtilSingleton.getInstancia().getUuid());
		
		System.out.println("UUID: "+IdUtilSingleton.getInstancia().getUuid());
		Assertions.assertNotNull(aplicativoSerivice.localizarPeloId(IdUtilSingleton.getInstancia().getUuid()).getExecutavel());
	}
	
	@Test
	@Order(3)
	void fazerDownloadExecutavel() {
		Assertions.assertTrue(aplicativoSerivice.download("download-teste", "app1.apk", IdUtilSingleton.getInstancia().getUuid()));
	}
	
	@Test
	@Order(4)
	void deleteArquivoBaixado() {
		try {
			Assertions.assertTrue(Files.deleteIfExists(Path.of("download-teste/app1.apk")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
