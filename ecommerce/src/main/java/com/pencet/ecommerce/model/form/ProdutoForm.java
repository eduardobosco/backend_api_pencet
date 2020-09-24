package com.pencet.ecommerce.model.form;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProdutoForm {

	private Integer id;
	private String nome;
	private String descricao;
	private Integer qtdEstoque;
	private Double valor;
	private Integer id_categoria;
	private Integer id_funcionario;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataFabricacao;

	public ProdutoForm() {
	}

	public ProdutoForm(Integer id, String nome, String descricao, Integer qtdEstoque, Double valor,
			Integer id_categoria, Integer id_funcionario, Instant dataFabricacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.valor = valor;
		this.id_categoria = id_categoria;
		this.id_funcionario = id_funcionario;
		this.dataFabricacao = dataFabricacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public Instant getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Instant dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoForm other = (ProdutoForm) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}


