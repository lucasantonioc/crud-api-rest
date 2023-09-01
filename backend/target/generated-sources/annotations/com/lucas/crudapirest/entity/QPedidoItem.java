package com.lucas.crudapirest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPedidoItem is a Querydsl query type for PedidoItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPedidoItem extends EntityPathBase<PedidoItem> {

    private static final long serialVersionUID = 55126486L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPedidoItem pedidoItem = new QPedidoItem("pedidoItem");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QPedido pedido;

    public final QProdutoServico produtoServico;

    public final NumberPath<java.math.BigDecimal> quantidade = createNumber("quantidade", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valorUnitarioVenda = createNumber("valorUnitarioVenda", java.math.BigDecimal.class);

    public QPedidoItem(String variable) {
        this(PedidoItem.class, forVariable(variable), INITS);
    }

    public QPedidoItem(Path<? extends PedidoItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPedidoItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPedidoItem(PathMetadata metadata, PathInits inits) {
        this(PedidoItem.class, metadata, inits);
    }

    public QPedidoItem(Class<? extends PedidoItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pedido = inits.isInitialized("pedido") ? new QPedido(forProperty("pedido")) : null;
        this.produtoServico = inits.isInitialized("produtoServico") ? new QProdutoServico(forProperty("produtoServico")) : null;
    }

}

