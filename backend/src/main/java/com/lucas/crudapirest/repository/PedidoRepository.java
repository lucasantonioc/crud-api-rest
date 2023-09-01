package com.lucas.crudapirest.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lucas.crudapirest.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID>, PedidoCustomRepository {
}