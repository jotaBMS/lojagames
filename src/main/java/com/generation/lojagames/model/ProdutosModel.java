package com.generation.lojagames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity  
@Table(name = "tb_produtos") 
public class ProdutosModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String nome;
	
	private int valor;
			
	@NotBlank
	@Size(min = 5, max = 100)
	private String plataforma;
	
	@ManyToOne //anotação para achave estrangeira na tabela tema
	@JsonIgnoreProperties("produtos") //anotação para não deixar uma resposta em loop no Json
	private CategoriasModel categorias;

	public CategoriasModel getCategorias() {
		return categorias;
	}

	public void setCategorias(CategoriasModel categorias) {
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
	
	
	
	

}
