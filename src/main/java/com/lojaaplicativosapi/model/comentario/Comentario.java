package com.lojaaplicativosapi.model.comentario;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 16)
	private UUID uuid;
	@Column(nullable = false, length = 25)
	private String autour;
	@Column(nullable = false, length = 500)	
	private String conteudo;
	private LocalDateTime data;

}
