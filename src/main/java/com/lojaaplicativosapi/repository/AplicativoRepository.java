package com.lojaaplicativosapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaaplicativosapi.enumtype.Tipo;
import com.lojaaplicativosapi.model.aplicativo.Aplicativo;

public interface AplicativoRepository extends JpaRepository<Aplicativo, UUID> {
	
	List<Aplicativo> findAllByTipo(Tipo tipo);
	Aplicativo findByNomeAndTipo(String nome, Tipo tipo);

}
