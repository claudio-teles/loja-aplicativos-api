package com.lojaaplicativosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaaplicativosapi.model.aplicativo.Aplicativo;

public interface AplicativoRepository extends JpaRepository<Aplicativo, Long> {

}
