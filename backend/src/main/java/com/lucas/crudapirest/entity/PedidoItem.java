package com.lucas.crudapirest.entity;

import jakarta.persistence.*;
import java.io.Serializable;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.util.UUID;
import java.math.BigDecimal;
import com.lucas.crudapirest.dto.PedidoItemUpdateDTO;
import static java.util.Optional.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "pedido_item")
public class PedidoItem implements Serializable {

	@Id
	@GeneratedValue
	private UUID id;

	@PositiveOrZero
	@Column(name = "valor_unitario_venda", nullable = false)
	private BigDecimal valorUnitarioVenda = BigDecimal.ZERO;

	@Positive
	@Column(nullable = false)
	private BigDecimal quantidade = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_servico_id", nullable = false)
	private ProdutoServico produtoServico;

	public void update(PedidoItemUpdateDTO pedidoItemUpdateDTO) {
		valorUnitarioVenda = ofNullable(pedidoItemUpdateDTO.getValorUnitarioVenda()).orElse(valorUnitarioVenda);
		quantidade = ofNullable(pedidoItemUpdateDTO.getQuantidade()).orElse(quantidade);
	}

}