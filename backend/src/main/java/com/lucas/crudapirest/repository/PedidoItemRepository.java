package com.lucas.crudapirest.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lucas.crudapirest.entity.PedidoItem;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID>, PedidoItemCustomRepository {

    List<PedidoItem> findAllByPedidoId(UUID pedidoId);

}