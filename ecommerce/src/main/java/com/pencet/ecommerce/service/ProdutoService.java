package com.pencet.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pencet.ecommerce.exception.CategoriaNotFoundException;
import com.pencet.ecommerce.exception.FuncionarioNotFoundException;
import com.pencet.ecommerce.exception.ParametroObrigatorioException;
import com.pencet.ecommerce.exception.ProdutoNotFoundException;
import com.pencet.ecommerce.model.Produto;
import com.pencet.ecommerce.model.form.ProdutoForm;
import com.pencet.ecommerce.repository.ProdutoRepository;

@Service

public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private FuncionarioService funcionarioService;

	public Produto inserir(ProdutoForm produto) throws CategoriaNotFoundException, FuncionarioNotFoundException {
		Produto prod = atualizaDados(produto);
		Produto produtoSalvoNoBd = produtoRepository.save(prod);
		return produtoSalvoNoBd;

	}

	private Produto atualizaDados(ProdutoForm produto) throws CategoriaNotFoundException, FuncionarioNotFoundException {
		Produto prod = new Produto();
		prod.setNome(produto.getNome());
		prod.setDescricao(produto.getDescricao());
		prod.setQtdEstoque(produto.getQtdEstoque());
		prod.setValor(produto.getValor());
		prod.setDataFabricacao(produto.getDataFabricacao());

		prod.setCategoria(categoriaService.listarPorId(produto.getId_categoria()));
		prod.setFuncionario(funcionarioService.listarPorId(produto.getId_funcionario()));

		return prod;

	}

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto listarProdutoPorId(Integer id) throws ProdutoNotFoundException {
		Optional<Produto> optionalProduto = produtoRepository.findById(id);

		if (optionalProduto.isPresent()) {
			return optionalProduto.get();
		}
		throw new ProdutoNotFoundException("Produto com id " + id + " não encontrado");
	}

	public Produto substituir(Integer id, Produto produto)
			throws ParametroObrigatorioException, ProdutoNotFoundException {
		if (produto == null)
			throw new ParametroObrigatorioException("Campo 'produto' é obrigatório");

		Produto produtoNoBanco = listarProdutoPorId(id);

		if (produto.getNome() != null) {
			produtoNoBanco.setNome(produto.getNome());
		}

		if (produto.getDescricao() != null) {
			produtoNoBanco.setDescricao(produto.getDescricao());
		}

		if (produto.getQtdEstoque() != null) {
			produtoNoBanco.setQtdEstoque(produto.getQtdEstoque());
		}

		if (produto.getValor() != null) {
			produtoNoBanco.setValor(produto.getValor());
		}

		if (produto.getDataFabricacao() != null) {
			produtoNoBanco.setDataFabricacao(produto.getDataFabricacao());
		}

		return produtoRepository.save(produtoNoBanco);
	}

	public Produto atualizar(Integer id, Produto produto)
			throws ParametroObrigatorioException, ProdutoNotFoundException {
		if (produto == null)
			throw new ParametroObrigatorioException("Campo 'produto' é obrigatório");

		Produto produtoNoBanco = listarProdutoPorId(id);

		if (produto.getNome() != null) {
			produtoNoBanco.setNome(produto.getNome());
		}

		if (produto.getDescricao() != null) {
			produtoNoBanco.setDescricao(produto.getDescricao());
		}

		if (produto.getQtdEstoque() != null) {
			produtoNoBanco.setQtdEstoque(produto.getQtdEstoque());
		}

		if (produto.getValor() != null) {
			produtoNoBanco.setValor(produto.getValor());
		}

		if (produto.getDataFabricacao() != null) {
			produtoNoBanco.setDataFabricacao(produto.getDataFabricacao());
		}

		return produtoRepository.save(produtoNoBanco);
	}

	public void deletar(Integer id) throws ProdutoNotFoundException {
		Produto produtoNoBanco = listarProdutoPorId(id);
		produtoRepository.delete(produtoNoBanco);
	}

}
