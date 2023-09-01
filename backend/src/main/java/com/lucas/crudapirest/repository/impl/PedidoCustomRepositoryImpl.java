package com.lucas.crudapirest.repository.impl;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.entity.Pedido;
import com.lucas.crudapirest.entity.QPedido;
import com.lucas.crudapirest.repository.PedidoCustomRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoCustomRepositoryImpl implements PedidoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public Page<Pedido> findAll(Pageable pageable, PedidoFilterDTO filter) {
        final QPedido pedido = QPedido.pedido;
        JPAQuery<Pedido> query = new JPAQuery<>(em)
                .select(pedido)
                .orderBy(pedido.id.asc());

        if (Objects.nonNull(filter.getStatus())) {
            query.where(pedido.status.eq(filter.getStatus()));
        }

        List<Pedido> results = query.fetch();
//                offset(pageable.getPageSize() * pageable.getPageNumber())
//                .limit(pageable.getPageSize())
//                .fetch();

        return new PageImpl<>(results, pageable, results.size());
    }

}
