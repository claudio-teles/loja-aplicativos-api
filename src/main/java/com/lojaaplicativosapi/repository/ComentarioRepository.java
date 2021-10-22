package com.lojaaplicativosapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaaplicativosapi.model.comentario.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {

}
