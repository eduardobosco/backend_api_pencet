package com.pencet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pencet.ecommerce.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
