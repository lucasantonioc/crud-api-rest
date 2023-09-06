package com.lucas.crudapirest.dto.filter;

import com.lucas.crudapirest.enumeration.EnumStatusProdutoServico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoServicoFilterDTO {

    private String descricao;

    private EnumStatusProdutoServico status;

}
