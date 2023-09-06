package com.lucas.crudapirest.dto;

import lombok.*;
import java.util.UUID;
import java.math.BigDecimal;
import com.lucas.crudapirest.enumeration.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProdutoServicoUpdateDTO {

	private BigDecimal valorUnitario;

	private String descricao;

}