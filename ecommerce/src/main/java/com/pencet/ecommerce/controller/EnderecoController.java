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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pencet.ecommerce.exception.EnderecoNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Endereco;
import com.pencet.ecommerce.service.EnderecoService;

@RestController

@RequestMapping("/endereco")

public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Endereco endereco) {
		enderecoService.inserir(endereco);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Endereco>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(enderecoService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> listarPorId(@PathVariable Integer id) throws EnderecoNotFoundException {
		Endereco endereco = enderecoService.listarPorId(id);

		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return new ResponseEntity<Endereco>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Endereco endereco)
			throws EnderecoNotFoundException, ParametroObrigatorioException {
		enderecoService.substituir(id, endereco);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws EnderecoNotFoundException {
		enderecoService.deletar(id);
	}

}