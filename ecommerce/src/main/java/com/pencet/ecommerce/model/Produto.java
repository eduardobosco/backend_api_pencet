package com.pencet.ecommerce.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PRODUTO")

public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
	
	@NotNull
	@Size(min=5, max=30)
	@Column(name="NOME", nullable=false , length=30)
		private String nome;
	
	@NotNull
	@Size(min=5, max=200)
	@Column(name="DESCRICAO", nullable=false , length=200)
		private String descricao;
	
	@NotNull
	@Column(name="QTDESTOQUE", nullable=false , length=20)
		private Integer qtdEstoque;
	
	@NotNull
	@Column(name="VALOR", nullable=false , length=10)
		private Double valor;
	
	@Past
	@Column(name="DATAFABRICACAO")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT" )
	   	private Instant dataFabricacao;

	public Produto() {};
	
	
	public Produto(Integer id, @NotNull @Size(min = 5, max = 30) String nome,
			@NotNull @Size(min = 5, max = 200) String descricao, @NotNull Integer qtdEstoque,
			@NotNull Double valor, @Past Instant dataFabricacao, Categoria categoria, Funcionario funcionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.qtdEstoque = qtdEstoque;
		this.valor = valor;
		this.dataFabricacao = dataFabricacao;
		this.categoria = categoria;
		this.funcionario = funcionario;
	}

	@ManyToOne()
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@ManyToOne()
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	
	@OneToMany(mappedBy = "id.produto")
	private Set<Carrinho> itens = new HashSet<>();
	
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

	public Instant getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Instant dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	@JsonIgnore
	public Set<Pedido> getPedido() {
		Set<Pedido> pedidos = new HashSet<>();
		for(Carrinho x : itens) {
			pedidos.add(x.getPedido());
		}
		return pedidos;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
