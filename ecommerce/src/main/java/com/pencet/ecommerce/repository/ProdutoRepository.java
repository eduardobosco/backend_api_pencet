package com.pencet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pencet.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
