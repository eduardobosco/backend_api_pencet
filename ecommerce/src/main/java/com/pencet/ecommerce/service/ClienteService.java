package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.ClienteNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Cliente;
import com.pencet.ecommerce.model.Endereco;
import com.pencet.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente inserir(Cliente cliente, Endereco endereco) {
		cliente.setEndereco(endereco);
		Cliente clienteSalvoNoBd = clienteRepository.save(cliente);
		return clienteSalvoNoBd;
	
	}
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	public Cliente listarPorId(Integer id) throws ClienteNotFoundException {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		
		if(optionalCliente.isPresent()) {
			return optionalCliente.get();
		}
		throw new ClienteNotFoundException("Cliente com id " + id + " não encontrada");
	}
	
	public Cliente substituir(Integer id, Cliente cliente) throws ParametroObrigatorioException, ClienteNotFoundException {
		if(cliente == null) throw new ParametroObrigatorioException("Campo 'cliente' é obrigatório");
		
		Cliente clienteNoBanco = listarPorId(id);
		
		if(cliente.getNome() != null) {
			clienteNoBanco.setNome(cliente.getNome());
		}
		
		if(cliente.getUsuario() != null) {
			clienteNoBanco.setUsuario(cliente.getUsuario());
		}
		
		if(cliente.getCpf() != null) {
			clienteNoBanco.setCpf(cliente.getCpf());
		}
		
		if(cliente.getEmail() != null) {
			clienteNoBanco.setEmail(cliente.getEmail());
		}
		
		if(cliente.getDataNascimento() != null) {
			clienteNoBanco.setDataNascimento(cliente.getDataNascimento());
		}
		
		return clienteRepository.save(clienteNoBanco);
	}
	
	public void deletar(Integer id) throws ClienteNotFoundException {
		Cliente clienteNoBanco = listarPorId(id);
		clienteRepository.delete(clienteNoBanco);
	}
		
}

