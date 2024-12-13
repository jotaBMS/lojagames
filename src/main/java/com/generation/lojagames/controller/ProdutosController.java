package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.ProdutosModel;
import com.generation.lojagames.repository.CategoriasRepository;
import com.generation.lojagames.repository.ProdutosRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	
	@GetMapping("/all")
	public ResponseEntity <List<ProdutosModel>> getAll(){
		
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutosModel> getById(@PathVariable Long id) {
		return produtosRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("nome/{nome}")
	public ResponseEntity<List<ProdutosModel>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
		
		}
	
	
	@PostMapping	
	public ResponseEntity<ProdutosModel> post(@Valid @RequestBody ProdutosModel produtosModel){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtosRepository.save(produtosModel));
    }
	
	
	@PutMapping
	public ResponseEntity<ProdutosModel> put(@Valid @RequestBody ProdutosModel produtosModel){
		
		return produtosRepository.findById(produtosModel.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(produtosRepository.save(produtosModel)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	   @DeleteMapping("/{id}")
	   public void delete(@PathVariable Long id) {
	       Optional<ProdutosModel> produtos = produtosRepository.findById(id);


	       if(produtos.isEmpty())
	           throw new ResponseStatusException(HttpStatus.NOT_FOUND);


	       produtosRepository.deleteById(id);
	   }
	
	
}
