package com.lucas.crudapirest.repository;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoCustomRepository {

    Page<Pedido> findAll(Pageable pageable, PedidoFilterDTO filter);

}
