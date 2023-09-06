package com.lucas.crudapirest.repository.impl;

import com.lucas.crudapirest.dto.filter.ProdutoServicoFilterDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import com.lucas.crudapirest.entity.QProdutoServico;
import com.lucas.crudapirest.repository.ProdutoServicoCustomRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class ProdutoServicoCustomRepositoryImpl implements ProdutoServicoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<ProdutoServico> findAll(Pageable pageable, ProdutoServicoFilterDTO filter) {
        final QProdutoServico produtoServico = QProdutoServico.produtoServico;

        var query = new JPAQueryFactory(em)
                .selectFrom(produtoServico);

        addFilters(filter, produtoServico, query);

        var results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        var queryCount = new JPAQueryFactory(em)
                .select(produtoServico.id)
                .from(produtoServico);
        addFilters(filter, produtoServico, queryCount);

        long total = queryCount.fetch().size();

        return new PageImpl<>(results, pageable, total);
    }

    private static void addFilters(ProdutoServicoFilterDTO filter, QProdutoServico produtoServico, JPAQuery query) {
        if (Objects.nonNull(filter.getStatus())) {
            query.where(produtoServico.status.eq(filter.getStatus()));
        }
        if (Objects.nonNull(filter.getDescricao())) {
            query.where(produtoServico.descricao.eq(filter.getDescricao()));
        }
    }

}
