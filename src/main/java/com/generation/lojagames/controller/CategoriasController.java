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

import com.generation.lojagames.model.CategoriasModel;
import com.generation.lojagames.repository.CategoriasRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

	@Autowired
	private CategoriasRepository categoriasRepository;
	
	
	@GetMapping("/all")
	public ResponseEntity <List<CategoriasModel>> getAll(){
		
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriasModel> getById(@PathVariable Long id) {
		return categoriasRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("categoria/{categoria}")
	public ResponseEntity<List<CategoriasModel>> getByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(categoriasRepository.findAllByCategoriaContainingIgnoreCase(categoria));
		
		}
	
	
	@PostMapping	
	public ResponseEntity<CategoriasModel> post(@Valid @RequestBody CategoriasModel categoriasModel){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriasRepository.save(categoriasModel));
    }
	
	
	@PutMapping
	public ResponseEntity<CategoriasModel> put(@Valid @RequestBody CategoriasModel categoriasModel){
		
		return categoriasRepository.findById(categoriasModel.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(categoriasRepository.save(categoriasModel)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
	}
	
	
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	   @DeleteMapping("/{id}")
	   public void delete(@PathVariable Long id) {
	       Optional<CategoriasModel> categorias = categoriasRepository.findById(id);


	       if(categorias.isEmpty())
	           throw new ResponseStatusException(HttpStatus.NOT_FOUND);


	       categoriasRepository.deleteById(id);
	   }
	
	
}
