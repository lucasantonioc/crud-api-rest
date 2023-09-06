package com.lucas.crudapirest.repository;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.dto.filter.PedidoItemFilterDTO;
import com.lucas.crudapirest.entity.Pedido;
import com.lucas.crudapirest.entity.PedidoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoItemCustomRepository {

    Page<PedidoItem> findAll(Pageable pageable, PedidoItemFilterDTO filter);

}
