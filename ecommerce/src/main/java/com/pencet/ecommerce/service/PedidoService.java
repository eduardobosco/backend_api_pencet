package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.PedidoNotFoundException;
import com.pencet.ecommerce.model.Pedido;
import com.pencet.ecommerce.repository.PedidoRepository;

@Service

public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	
	public Pedido inserir(Pedido pedido) {
		Pedido pedidoSalvoNoBd = pedidoRepository.save(pedido);
		return pedidoSalvoNoBd;
	
	}
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	public Pedido listarPorId(Integer id) throws PedidoNotFoundException {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
		
		if(optionalPedido.isPresent()) {
			return optionalPedido.get();
		}
		throw new PedidoNotFoundException("Pedido com id " + id + " não encontrado");
	}
	
		
	public void deletar(Integer id) throws PedidoNotFoundException {
		Pedido pedidoNoBanco = listarPorId(id);
		pedidoRepository.delete(pedidoNoBanco);
	}

}
