package com.lojaaplicativosapi.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComentarioDTO {
	
	private UUID uuidAplicativo;
	private String autor;
	private String conteudo;

}
