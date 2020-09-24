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

import com.pencet.ecommerce.exception.FuncionarioNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Funcionario;
import com.pencet.ecommerce.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")

public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Funcionario funcionario) {
		funcionarioService.inserir(funcionario);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(funcionarioService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> listarPorId(@PathVariable Integer id) throws FuncionarioNotFoundException {
		Funcionario funcionario = funcionarioService.listarPorId(id);

		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		}
		return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Funcionario funcionario)
			throws FuncionarioNotFoundException, ParametroObrigatorioException {
		funcionarioService.substituir(id, funcionario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws FuncionarioNotFoundException {
		funcionarioService.deletar(id);
	}

}