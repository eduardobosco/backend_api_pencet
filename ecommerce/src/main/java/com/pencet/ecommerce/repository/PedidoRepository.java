package com.pencet.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pencet.ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
