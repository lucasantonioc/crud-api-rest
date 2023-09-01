package com.lucas.crudapirest.dto;

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

	private BigDecimal valorBruto = BigDecimal.ZERO;

	private BigDecimal valorLiquido = BigDecimal.ZERO;

	private BigDecimal percentualDesconto = BigDecimal.ZERO;

	private String observacao;

	@NotNull
	private EnumStatusPedido status;

}