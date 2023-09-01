package com.lucas.crudapirest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdutoServico is a Querydsl query type for ProdutoServico
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProdutoServico extends EntityPathBase<ProdutoServico> {

    private static final long serialVersionUID = -979786652L;

    public static final QProdutoServico produtoServico = new QProdutoServico("produtoServico");

    public final StringPath descricao = createString("descricao");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final EnumPath<com.lucas.crudapirest.enumeration.EnumStatusProdutoServico> status = createEnum("status", com.lucas.crudapirest.enumeration.EnumStatusProdutoServico.class);

    public final EnumPath<com.lucas.crudapirest.enumeration.EnumTipoProdutoServico> tipo = createEnum("tipo", com.lucas.crudapirest.enumeration.EnumTipoProdutoServico.class);

    public final NumberPath<java.math.BigDecimal> valorUnitario = createNumber("valorUnitario", java.math.BigDecimal.class);

    public QProdutoServico(String variable) {
        super(ProdutoServico.class, forVariable(variable));
    }

    public QProdutoServico(Path<? extends ProdutoServico> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdutoServico(PathMetadata metadata) {
        super(ProdutoServico.class, metadata);
    }

}

