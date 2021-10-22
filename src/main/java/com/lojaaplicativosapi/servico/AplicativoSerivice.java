package com.lojaaplicativosapi.servico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojaaplicativosapi.model.aplicativo.Aplicativo;
import com.lojaaplicativosapi.repository.AplicativoRepository;

@Service
public class AplicativoSerivice {
	
	@Autowired
	private AplicativoRepository aplicativoRepository;
	
	public Aplicativo cadastrarOuAtualizar(Aplicativo aplicativo) {
		if (aplicativo != null) {
			return aplicativoRepository.save(aplicativo);
		}
		return null;
	}
	
	public Aplicativo localizarPeloId(UUID id) {
		return aplicativoRepository.getById(id);
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

}
