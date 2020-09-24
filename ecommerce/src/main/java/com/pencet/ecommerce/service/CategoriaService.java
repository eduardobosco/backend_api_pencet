package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.CategoriaNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.model.Categoria;
import com.pencet.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria inserir(Categoria categoria) {
		Categoria categoriaSalvoNoBd = categoriaRepository.save(categoria);
		return categoriaSalvoNoBd;

	}

	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	public Categoria listarPorId(Integer id) throws CategoriaNotFoundException {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

		if (optionalCategoria.isPresent()) {
			return optionalCategoria.get();
		}
		throw new CategoriaNotFoundException("Categoria com id " + id + " não encontrada");
	}

	public Categoria substituir(Integer id, Categoria categoria)
			throws ParametroObrigatorioException, CategoriaNotFoundException {
		if (categoria == null)
			throw new ParametroObrigatorioException("Campo 'categoria' é obrigatório");

		Categoria categoriaNoBanco = listarPorId(id);

		if (categoria.getNome() != null) {
			categoriaNoBanco.setNome(categoria.getNome());
		}

		if (categoria.getDescricao() != null) {
			categoriaNoBanco.setDescricao(categoria.getDescricao());
		}

		return categoriaRepository.save(categoriaNoBanco);
	}

	public void deletar(Integer id) throws CategoriaNotFoundException {
		Categoria categoriaNoBanco = listarPorId(id);
		categoriaRepository.delete(categoriaNoBanco);
	}

}