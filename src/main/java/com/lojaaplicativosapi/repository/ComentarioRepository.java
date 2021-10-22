package com.lojaaplicativosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaaplicativosapi.model.comentario.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
