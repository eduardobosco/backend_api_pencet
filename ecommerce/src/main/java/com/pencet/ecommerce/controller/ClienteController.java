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

import com.pencet.ecommerce.exception.ClienteNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Cliente;
import com.pencet.ecommerce.model.Endereco;
import com.pencet.ecommerce.model.form.ClienteForm;
import com.pencet.ecommerce.service.ClienteService;
import com.pencet.ecommerce.service.EnderecoService;

@RestController

@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteForm cliente) {
		Cliente cli = new Cliente(cliente.getId(), cliente.getNome(), cliente.getUsuario(), cliente.getCpf(), cliente.getEmail(), cliente.getDataNascimento());
		Endereco end = new Endereco(cliente.getId(), cliente.getRua(), cliente.getNumero(), cliente.getComplemento(), cliente.getBairro(), cliente.getCidade(), cliente.getEstado(), cliente.getCep(), cli);
		clienteService.inserir(cli, end);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(clienteService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable Integer id) throws ClienteNotFoundException {
		Cliente cliente = clienteService.listarPorId(id);

		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Cliente cliente)
			throws ClienteNotFoundException, ParametroObrigatorioException {
		clienteService.substituir(id, cliente);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws ClienteNotFoundException {
		clienteService.deletar(id);
	}
	
}
