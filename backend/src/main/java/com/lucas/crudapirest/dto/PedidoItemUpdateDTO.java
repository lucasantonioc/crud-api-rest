package com.lucas.crudapirest.dto;

import lombok.*;
import java.util.UUID;
import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoItemUpdateDTO {

	private UUID id;

	private BigDecimal valorUnitarioVenda;

	private BigDecimal quantidade;

}