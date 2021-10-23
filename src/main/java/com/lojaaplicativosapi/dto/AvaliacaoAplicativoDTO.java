package com.lojaaplicativosapi.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvaliacaoAplicativoDTO {
	
	private UUID uuid;
	private int avaliacaoIndividual;

}
