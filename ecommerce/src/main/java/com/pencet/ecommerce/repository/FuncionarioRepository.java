package com.pencet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pencet.ecommerce.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
