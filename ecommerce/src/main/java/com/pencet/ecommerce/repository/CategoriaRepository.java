package com.pencet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pencet.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
