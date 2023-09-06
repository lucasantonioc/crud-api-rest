package com.lucas.crudapirest.repository.impl;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.entity.Pedido;
import com.lucas.crudapirest.entity.QPedido;
import com.lucas.crudapirest.repository.PedidoCustomRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

public class PedidoCustomRepositoryImpl implements PedidoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public Page<Pedido> findAll(Pageable pageable, PedidoFilterDTO filter) {
        final QPedido pedido = QPedido.pedido;

        var query = new JPAQueryFactory(em)
                .selectFrom(pedido);

        addFilters(filter, pedido, query);

        var results = query.
                offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var queryCount = new JPAQueryFactory(em)
                .select(pedido.id)
                .from(pedido);
        addFilters(filter, pedido, queryCount);

        long total = queryCount.fetch().size();

        return new PageImpl<>(results, pageable, Optional.ofNullable(total).orElse(0L));
    }

    private void addFilters(PedidoFilterDTO filter, QPedido pedido, JPAQuery query) {
        if (Objects.nonNull(filter.getStatus())) {
            query.where(pedido.status.eq(filter.getStatus()));
        }
    }

}
