package com.pencet.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ENDERECO")

public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(name = "RUA", nullable = false, length = 60)
	private String rua;

	@NotNull
	@Column(name = "NUMERO", nullable = false, length = 30)
	private Integer numero;

	@Column(name = "COMPLEMENTO", nullable = false, length = 30)
	private String complemento;

	@NotNull
	@Column(name = "BAIRRO", nullable = false, length = 40)
	private String bairro;

	@NotNull
	@Column(name = "CIDADE", nullable = false, length = 40)
	private String cidade;

	@NotNull
	@Column(name = "ESTADO", nullable = false, length = 40)
	private String estado;

	@NotNull
	@Column(name = "CEP", nullable = false, length = 40)
	private String cep;

	public Endereco() {
	}

	public Endereco(Integer id, @NotNull String rua,@NotNull Integer numero, @NotNull String complemento,
			@NotNull String bairro, @NotNull String cidade,
			@NotNull String estado, @NotNull String cep, Cliente cliente) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.cliente = cliente;
	}
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}