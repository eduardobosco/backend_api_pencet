package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.FuncionarioNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Funcionario;
import com.pencet.ecommerce.repository.FuncionarioRepository;

@Service

public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	
	
	public Funcionario inserir(Funcionario funcionario) {
		Funcionario funcionarioSalvoNoBd = funcionarioRepository.save(funcionario);
		return funcionarioSalvoNoBd;
	}

	public List<Funcionario> listar() {
		return funcionarioRepository.findAll();
	}

	public Funcionario listarPorId(Integer id) throws FuncionarioNotFoundException {
		Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

		if (optionalFuncionario.isPresent()) {
			return optionalFuncionario.get();
		}
		throw new FuncionarioNotFoundException("Funcionario com id " + id + " não encontrado");
	}

	public Funcionario substituir(Integer id, Funcionario funcionario)
			throws ParametroObrigatorioException, FuncionarioNotFoundException {
		if (funcionario == null)
			throw new ParametroObrigatorioException("Campo 'funcionario' é obrigatório");

		Funcionario funcionarioNoBanco = listarPorId(id);

		if (funcionario.getNome() != null) {
			funcionarioNoBanco.setNome(funcionario.getNome());
		}

		if (funcionario.getCpf() != null) {
			funcionarioNoBanco.setCpf(funcionario.getCpf());
		}

		return funcionarioRepository.save(funcionarioNoBanco);
	}

	public void deletar(Integer id) throws FuncionarioNotFoundException {
		Funcionario funcionarioNoBanco = listarPorId(id);
		funcionarioRepository.delete(funcionarioNoBanco);
	}

}