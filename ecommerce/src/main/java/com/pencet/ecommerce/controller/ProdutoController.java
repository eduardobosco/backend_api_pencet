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

import com.pencet.ecommerce.exception.CategoriaNotFoundException;
import com.pencet.ecommerce.exception.FuncionarioNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.exception.ProdutoNotFoundException;
import com.pencet.ecommerce.model.Produto;
import com.pencet.ecommerce.model.form.ProdutoForm;
import com.pencet.ecommerce.service.ProdutoService;

@RestController

@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody ProdutoForm produto) throws CategoriaNotFoundException, FuncionarioNotFoundException {
		produtoService.inserir(produto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(produtoService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Integer id) throws ProdutoNotFoundException {
		Produto produto = produtoService.listarProdutoPorId(id);

		if (produto != null) {
			return ResponseEntity.ok(produto);
		}
		return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Produto produto)
			throws ProdutoNotFoundException, ParametroObrigatorioException {
		produtoService.substituir(id, produto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/funcionario/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody(required = true) Produto produto)
			throws ProdutoNotFoundException, ParametroObrigatorioException {
		produtoService.substituir(id, produto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws ProdutoNotFoundException {
		produtoService.deletar(id);
	}
	

}
