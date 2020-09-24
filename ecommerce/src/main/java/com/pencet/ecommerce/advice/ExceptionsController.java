package com.pencet.ecommerce.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pencet.ecommerce.exception.CarrinhoNotFoundException;
import com.pencet.ecommerce.exception.CategoriaNotFoundException;
import com.pencet.ecommerce.exception.ClienteNotFoundException;
import com.pencet.ecommerce.exception.EnderecoNotFoundException;
import com.pencet.ecommerce.exception.FuncionarioNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.exception.PedidoNotFoundException;
import com.pencet.ecommerce.exception.ProdutoNotFoundException;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(ProdutoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(PedidoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}

	@ExceptionHandler(ParametroObrigatorioException.class)
	public ResponseEntity<Void> trataParametroObrigatorio(ParametroObrigatorioException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(FuncionarioNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(FuncionarioNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(EnderecoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(EnderecoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(ClienteNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(CategoriaNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(CategoriaNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(CarrinhoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(CarrinhoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> trataValidacoes(MethodArgumentNotValidException exception) {
		
		Map<String, String> errosMap = new HashMap<String, String>();
		
		List<ObjectError> errosEncontrados = exception.getBindingResult().getAllErrors();
		
		for (ObjectError erro : errosEncontrados) {
			
			FieldError fieldError = (FieldError) erro;
			
			String atributo = fieldError.getField();
			String mensagem = fieldError.getDefaultMessage();
			errosMap.put(atributo, mensagem);
		}
		return ResponseEntity.badRequest().body(errosMap);
	}
}
