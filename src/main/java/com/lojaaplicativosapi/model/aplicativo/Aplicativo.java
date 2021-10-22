package com.lojaaplicativosapi.model.aplicativo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lojaaplicativosapi.enumtype.Tipo;
import com.lojaaplicativosapi.model.comentario.Comentario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Aplicativo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4850045954166097143L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 16)
	private UUID uuid;
	@Column(nullable = false, length = 50)
	private String nome;
	private BigDecimal preco;
	@Column(nullable = false)
	private Tipo tipo;
	@Column(nullable = false, length = 120)
	private String descricao;
	@Lob
	@Column(nullable = true)
	@Builder.Default
	private byte[] executavel = null;
	@Builder.Default
	private byte avaliacaoGeral = 0;
	@Builder.Default
	private byte avaliacaoIndividual = 0;
	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Comentario> comentarios;

}
