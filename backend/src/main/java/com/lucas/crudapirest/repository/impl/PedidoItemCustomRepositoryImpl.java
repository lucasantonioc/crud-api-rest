package com.lucas.crudapirest.repository.impl;

import com.lucas.crudapirest.dto.filter.PedidoItemFilterDTO;
import com.lucas.crudapirest.entity.PedidoItem;
import com.lucas.crudapirest.entity.QPedidoItem;
import com.lucas.crudapirest.repository.PedidoItemCustomRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.Optional;

public class PedidoItemCustomRepositoryImpl implements PedidoItemCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<PedidoItem> findAll(Pageable pageable, PedidoItemFilterDTO filter) {
        final QPedidoItem pedidoItem = QPedidoItem.pedidoItem;

        var query = new JPAQueryFactory(em)
                .selectFrom(pedidoItem);

        addFilters(filter, pedidoItem, query);

        var results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var queryCount = new JPAQueryFactory(em)
                .select(pedidoItem.id)
                .from(pedidoItem);
        addFilters(filter, pedidoItem, queryCount);

        long total = queryCount.fetch().size();

        return new PageImpl<>(results, pageable, Optional.ofNullable(total).orElse(0L));
    }

    private void addFilters(PedidoItemFilterDTO filter, QPedidoItem pedidoItem, JPAQuery query) {
        if (Objects.nonNull(filter.getPedido())) {
            query.where(pedidoItem.pedido.eq(filter.getPedido()));
        }
        if (Objects.nonNull(filter.getProdutoServico())) {
            query.where(pedidoItem.produtoServico.eq(filter.getProdutoServico()));
        }
    }
}
