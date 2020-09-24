package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.CarrinhoNotFoundException;
import com.pencet.ecommerce.model.Carrinho;
import com.pencet.ecommerce.repository.CarrinhoRepository;

	@Service
	public class CarrinhoService {

		@Autowired
		private CarrinhoRepository carrinhoRepository;

		
		public Carrinho inserir(Carrinho carrinho) {
			Carrinho carrinhoSalvoNoBd = carrinhoRepository.save(carrinho);
			return carrinhoSalvoNoBd;
		
		}
		
		public List<Carrinho> listar() {
			return carrinhoRepository.findAll();
		}
		
		public Carrinho listarPorId(Integer id) throws CarrinhoNotFoundException {
			Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(id);
			
			if(optionalCarrinho.isPresent()) {
				return optionalCarrinho.get();
			}
			throw new CarrinhoNotFoundException("Carrinho com id " + id + " não encontrada");
		}
		/*
		public Carrinho substituir(Integer id, Carrinho carrinho) throws ParametroObrigatorioException, CarrinhoNotFoundException {
			if(carrinho == null) throw new ParametroObrigatorioException("Campo 'carrinho' é obrigatório");
			
			Carrinho carrinhoNoBanco = listarPorId(id);
			
			if(carrinho.getId() != null) {
				carrinhoNoBanco.setId(carrinho.getId());
			}
			
			if(carrinho.getQtditens() != null) {
				carrinhoNoBanco.setQtditens(carrinho.getQtditens());
			}
						
			return carrinhoRepository.save(carrinhoNoBanco);
		}*/
		
		public void deletar(Integer id) throws CarrinhoNotFoundException {
			Carrinho carrinhoNoBanco = listarPorId(id);
			carrinhoRepository.delete(carrinhoNoBanco);
		}
			
	}

