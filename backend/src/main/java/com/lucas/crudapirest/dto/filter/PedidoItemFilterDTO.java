package com.lucas.crudapirest.dto.filter;

import com.lucas.crudapirest.entity.Pedido;
import com.lucas.crudapirest.entity.ProdutoServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItemFilterDTO {

    private Pedido pedido;

    private ProdutoServico produtoServico;

}
