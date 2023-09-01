package com.lucas.crudapirest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPedido is a Querydsl query type for Pedido
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPedido extends EntityPathBase<Pedido> {

    private static final long serialVersionUID = 1851458595L;

    public static final QPedido pedido = new QPedido("pedido");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final ListPath<PedidoItem, QPedidoItem> itens = this.<PedidoItem, QPedidoItem>createList("itens", PedidoItem.class, QPedidoItem.class, PathInits.DIRECT2);

    public final StringPath observacao = createString("observacao");

    public final NumberPath<java.math.BigDecimal> percentualDesconto = createNumber("percentualDesconto", java.math.BigDecimal.class);

    public final EnumPath<com.lucas.crudapirest.enumeration.EnumStatusPedido> status = createEnum("status", com.lucas.crudapirest.enumeration.EnumStatusPedido.class);

    public final NumberPath<java.math.BigDecimal> valorBruto = createNumber("valorBruto", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valorLiquido = createNumber("valorLiquido", java.math.BigDecimal.class);

    public QPedido(String variable) {
        super(Pedido.class, forVariable(variable));
    }

    public QPedido(Path<? extends Pedido> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPedido(PathMetadata metadata) {
        super(Pedido.class, metadata);
    }

}

