package com.lojaaplicativosapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojaaplicativosapi.model.aplicativo.Aplicativo;
import com.lojaaplicativosapi.service.AplicativoSerivice;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class AplicatvoController {
	
	@Autowired
	private AplicativoSerivice aplicativoSerivice;
	
	@PostMapping("/aplicativo")
	@ResponseStatus(HttpStatus.CREATED)
	public Aplicativo cadastrar(@RequestBody Aplicativo aplicativo) {
		return aplicativoSerivice.cadastrarOuAtualizar(aplicativo);
	}
	
	@GetMapping("/aplicativo/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public Aplicativo encontrarPeloId(@PathVariable("uuid") UUID uuid) {
		return aplicativoSerivice.localizarPeloId(uuid);
	}

}
