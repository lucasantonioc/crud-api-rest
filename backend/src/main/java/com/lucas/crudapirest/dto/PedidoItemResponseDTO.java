package com.lucas.crudapirest.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.UUID;
import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoItemResponseDTO {

	private UUID id;

	private BigDecimal valorUnitarioVenda;

	private BigDecimal quantidade;

}