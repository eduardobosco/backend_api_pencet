package com.pencet.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pencet.ecommerce.model.pk.CarrinhoPk;

@Entity
@Table(name = "CARRINHO")

public class Carrinho {

	@EmbeddedId
	private CarrinhoPk id = new CarrinhoPk();

	@NotNull
	@Column(name = "QTDITENS", nullable = false)
	private Integer qtdItens;

	private Double valor;

	public Carrinho() {
	}

	public Carrinho(Pedido pedido, Produto produto, Double valor, Integer qtditens) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.valor = valor;
		this.qtdItens = qtditens;
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
		;
	}
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
		;
	}

	public Integer getQtdItens() {
		return qtdItens;
	}

	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSubTotal() {
		return valor * qtdItens;
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
		Carrinho other = (Carrinho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
