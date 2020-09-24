package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.EnderecoNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Endereco;
import com.pencet.ecommerce.repository.EnderecoRepository;

@Service

public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco inserir(Endereco endereco) {
		Endereco enderecoSalvoNoBd = enderecoRepository.save(endereco);
		return enderecoSalvoNoBd;

	}

	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	public Endereco listarPorId(Integer id) throws EnderecoNotFoundException {
		Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);

		if (optionalEndereco.isPresent()) {
			return optionalEndereco.get();
		}
		throw new EnderecoNotFoundException("Endereco com id " + id + " não encontrada");
	}

	public Endereco substituir(Integer id, Endereco endereco)
			throws ParametroObrigatorioException, EnderecoNotFoundException {
		if (endereco == null)
			throw new ParametroObrigatorioException("Campo 'endereco' é obrigatório");

		Endereco enderecoNoBanco = listarPorId(id);

		if (endereco.getRua() != null) {
			enderecoNoBanco.setRua(endereco.getRua());
		}

		if (endereco.getNumero() != null) {
			enderecoNoBanco.setNumero(endereco.getNumero());
		}

		if (endereco.getComplemento() != null) {
			enderecoNoBanco.setComplemento(endereco.getComplemento());
		}

		if (endereco.getBairro() != null) {
			enderecoNoBanco.setBairro(endereco.getBairro());
		}

		if (endereco.getCidade() != null) {
			enderecoNoBanco.setCidade(endereco.getCidade());
		}

		if (endereco.getEstado() != null) {
			enderecoNoBanco.setEstado(endereco.getEstado());
		}

		if (endereco.getCep() != null) {
			enderecoNoBanco.setCep(endereco.getCep());
		}

		return enderecoRepository.save(enderecoNoBanco);
	}

	public void deletar(Integer id) throws EnderecoNotFoundException {
		Endereco enderecoNoBanco = listarPorId(id);
		enderecoRepository.delete(enderecoNoBanco);
	}

}