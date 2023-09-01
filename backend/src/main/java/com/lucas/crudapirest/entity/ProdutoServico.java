package com.lucas.crudapirest.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.UUID;
import java.math.BigDecimal;
import com.lucas.crudapirest.enumeration.*;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;

import static com.lucas.crudapirest.enumeration.EnumStatusProdutoServico.ATIVO;
import static com.lucas.crudapirest.enumeration.EnumTipoProdutoServico.PRODUTO;
import static java.util.Optional.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "produto_servico")
public class ProdutoServico implements Serializable {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(name = "valor_unitario", nullable = false)
	private BigDecimal valorUnitario = BigDecimal.ZERO;

	@Column(nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumStatusProdutoServico status;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumTipoProdutoServico tipo;

	//TODO desativar produto

	public boolean isProduto() {
		return this.tipo == PRODUTO;
	}

	public boolean isAtivo() {
		return this.status == ATIVO;
	}

	public void update(ProdutoServicoUpdateDTO produtoServicoUpdateDTO) {
		valorUnitario = ofNullable(produtoServicoUpdateDTO.getValorUnitario()).orElse(valorUnitario);
		descricao = ofNullable(produtoServicoUpdateDTO.getDescricao()).orElse(descricao);
		status = ofNullable(produtoServicoUpdateDTO.getStatus()).orElse(status);
		tipo = ofNullable(produtoServicoUpdateDTO.getTipo()).orElse(tipo);
	}

}