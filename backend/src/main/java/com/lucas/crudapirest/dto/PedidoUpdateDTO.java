package com.lucas.crudapirest.dto;

import lombok.*;
import java.math.BigDecimal;
import com.lucas.crudapirest.enumeration.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoUpdateDTO {

	private BigDecimal percentualDesconto;

	private EnumStatusPedido status;

	private String observacao;

}