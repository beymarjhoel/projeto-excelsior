package com.generation.excelsior.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.excelsior.model.Pokemon;
import com.generation.excelsior.repository.PokemonRepository;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin("*")
public class PokemonController {

	@Autowired
	private PokemonRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Pokemon>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Pokemon> GetById (@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Pokemon>> GetByTipo (@PathVariable String tipo) {
		return ResponseEntity.ok(repository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@PostMapping
	public ResponseEntity<Pokemon> post (@RequestBody Pokemon pokemon) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pokemon));
	}
	
	@PutMapping
	public ResponseEntity<Pokemon> put (@RequestBody Pokemon pokemon) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(pokemon));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
