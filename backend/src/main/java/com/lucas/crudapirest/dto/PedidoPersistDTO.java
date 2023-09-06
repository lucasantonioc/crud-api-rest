package com.lucas.crudapirest.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import com.lucas.crudapirest.enumeration.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoPersistDTO {

	@Builder.Default
	@PositiveOrZero
	private BigDecimal valorBruto = BigDecimal.ZERO;

	@Builder.Default
	@PositiveOrZero
	private BigDecimal valorLiquido = BigDecimal.ZERO;

	@Builder.Default
	@PositiveOrZero
	private BigDecimal percentualDesconto = BigDecimal.ZERO;

	private String observacao;

	@NotNull
	private EnumStatusPedido status;

}