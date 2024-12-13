package com.generation.lojagames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "tb_categorias") 
public class CategoriasModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String categoria;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String descricao;
	
			// fetch.lazy: pega sómente oque esta sendo pedido no momento e não vai carregar todo o banco de dados de uma vez
			//cascade: tudo que for feito nesse tema efetara as postagens relacionadas, no caso do remove, ao apagar esse tema todas as postagens relacionadas serão pagadas também
			@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorias", cascade = CascadeType.REMOVE) 
			@JsonIgnoreProperties("categorias") //anotação para não deixar uma resposta em loop no Json
			private List<ProdutosModel> produtos;
	
			
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
	
	
	
}
