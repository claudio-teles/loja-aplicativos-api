package com.lojaaplicativosapi.servico;

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
	
	public Aplicativo localizarPeloId(Long id) {
		return aplicativoRepository.getById(id);
	}

}
