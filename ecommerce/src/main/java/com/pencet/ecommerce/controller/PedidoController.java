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

import com.pencet.ecommerce.exception.PedidoNotFoundException;
import com.pencet.ecommerce.model.Pedido;
import com.pencet.ecommerce.service.PedidoService;

@RestController
@RequestMapping ("/pedido")

public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody Pedido pedido) {
		pedidoService.inserir(pedido);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(pedidoService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPorId(@PathVariable Integer id) throws PedidoNotFoundException {
		Pedido pedido = pedidoService.listarPorId(id);

		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		}
		return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws PedidoNotFoundException {
		pedidoService.deletar(id);
	}
	

}
