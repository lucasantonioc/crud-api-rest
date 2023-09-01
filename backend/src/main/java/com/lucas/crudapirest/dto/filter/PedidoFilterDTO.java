package com.lucas.crudapirest.dto.filter;

import com.lucas.crudapirest.enumeration.EnumStatusPedido;
import lombok.*;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFilterDTO {

//    private Pageable pageable;

    private EnumStatusPedido status;

}
