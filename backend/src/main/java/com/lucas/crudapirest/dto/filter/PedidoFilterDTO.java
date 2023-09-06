package com.lucas.crudapirest.dto.filter;

import com.lucas.crudapirest.enumeration.EnumStatusPedido;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFilterDTO {

    private EnumStatusPedido status;

}
