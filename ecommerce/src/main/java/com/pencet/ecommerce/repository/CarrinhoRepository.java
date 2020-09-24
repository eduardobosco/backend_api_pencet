package com.pencet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pencet.ecommerce.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

}
