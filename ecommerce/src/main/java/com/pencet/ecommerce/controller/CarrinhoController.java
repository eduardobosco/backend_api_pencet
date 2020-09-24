package com.pencet.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pencet.ecommerce.exception.CarrinhoNotFoundException;
import com.pencet.ecommerce.model.Carrinho;
import com.pencet.ecommerce.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")

public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Carrinho carrinho) {
		carrinhoService.inserir(carrinho);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Carrinho>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(carrinhoService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carrinho> listarPorId(@PathVariable Integer id) throws CarrinhoNotFoundException {
		Carrinho carrinho = carrinhoService.listarPorId(id);

		if (carrinho != null) {
			return ResponseEntity.ok(carrinho);
		}
		return new ResponseEntity<Carrinho>(HttpStatus.NOT_FOUND);

	}
/*
	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Carrinho carrinho)
			throws CarrinhoNotFoundException, ParametroObrigatorioException {
		carrinhoService.substituir(id, carrinho);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}*/

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws CarrinhoNotFoundException {
		carrinhoService.deletar(id);
	}

}
