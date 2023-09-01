package com.lucas.crudapirest.dto;

import lombok.*;
import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import com.lucas.crudapirest.enumeration.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoResponseDTO {

	private UUID id;

	private BigDecimal valorBruto;

	private BigDecimal valorLiquido;

	private BigDecimal percentualDesconto;

	private String observacao;

	private EnumStatusPedido status;

}