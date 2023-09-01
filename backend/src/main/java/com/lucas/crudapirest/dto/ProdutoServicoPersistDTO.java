package com.lucas.crudapirest.dto;

import lombok.*;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import com.lucas.crudapirest.enumeration.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProdutoServicoPersistDTO {

	@NotNull
	private BigDecimal valorUnitario;

	@NotNull
	private String descricao;

	@NotNull
	private EnumStatusProdutoServico status;

	@NotNull
	private EnumTipoProdutoServico tipo;

}