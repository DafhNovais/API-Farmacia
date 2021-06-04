package com.generation.farmacia.controller;

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

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping ("/produtos")
@CrossOrigin ("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Produto> GetById (@PathVariable Long id) {
		return repository.findById(id).map(rest -> ResponseEntity.ok(rest))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/produto/{produto}")
	public ResponseEntity<List<Produto>> GetByProduto(@PathVariable String produto) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(produto));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
