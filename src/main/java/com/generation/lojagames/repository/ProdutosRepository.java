package com.generation.lojagames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojagames.model.ProdutosModel;




public interface ProdutosRepository extends JpaRepository<ProdutosModel, Long> {  
	
	public List<ProdutosModel> findAllByNomeContainingIgnoreCase(@Param("nome") String nome );
	
	public Optional<ProdutosModel> findByNome(@Param("nome") String nome);
	
}
