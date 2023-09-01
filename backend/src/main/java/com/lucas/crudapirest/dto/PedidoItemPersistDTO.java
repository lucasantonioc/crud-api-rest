package com.lucas.crudapirest.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoItemPersistDTO {

	@NotNull
	private BigDecimal valorUnitarioVenda = BigDecimal.ZERO;

	@NotNull
	private BigDecimal quantidade = BigDecimal.ZERO;

	private UUID pedidoId;

	private UUID produtoServicoId;

}