package com.lucas.crudapirest.entity;

import jakarta.persistence.*;
import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.math.BigDecimal;
import com.lucas.crudapirest.enumeration.*;
import com.lucas.crudapirest.dto.PedidoUpdateDTO;

import static com.lucas.crudapirest.enumeration.EnumStatusPedido.ABERTO;
import static com.lucas.crudapirest.enumeration.EnumStatusPedido.FECHADO;
import static java.util.Optional.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	@Id
	@GeneratedValue
	private UUID id;

	@Builder.Default
	@PositiveOrZero
	@Column(name = "valor_bruto", nullable = false)
	private BigDecimal valorBruto = BigDecimal.ZERO;

	@Builder.Default
	@PositiveOrZero
	@Column(name = "valor_liquido", nullable = false)
	private BigDecimal valorLiquido = BigDecimal.ZERO;

	@Builder.Default
	@PositiveOrZero
	@Column(name = "percentual_desconto", nullable = false)
	private BigDecimal percentualDesconto = BigDecimal.ZERO;

	@Column(name = "observacao")
	private String observacao;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumStatusPedido status = ABERTO;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
	private List<PedidoItem> itens = new ArrayList<>();

	public boolean isAberto() {
		return this.status == ABERTO;
	}

	public void update(PedidoUpdateDTO pedidoUpdateDTO) {
		if (!isAberto()) {
			throw new RuntimeException("Pedido fechado");
		}

		if (percentualDesconto.compareTo(pedidoUpdateDTO.getPercentualDesconto()) != 0) {
			this.aplicarDescontoSobreProdutos(pedidoUpdateDTO.getPercentualDesconto());
		}
		observacao = ofNullable(pedidoUpdateDTO.getObservacao()).orElse(observacao);
	}

	public void alterarStatus() {
		if (this.status == ABERTO) {
			this.status = FECHADO;
		} else {
			this.status = ABERTO;
		}
	}

	public void recalcularTotaisPedido() {
		this.aplicarDescontoSobreProdutos(this.getPercentualDesconto());
	}

	private void aplicarDescontoSobreProdutos(final BigDecimal percentualDesconto) {
		if (Objects.nonNull(percentualDesconto)) {
			this.percentualDesconto = percentualDesconto;
		}

		if (this.getItens().size() == 0) {
			return;
		}

		BigDecimal valorTotalProdutos = BigDecimal.ZERO;
		for (PedidoItem item : this.getItens()) {
			if (item.getProdutoServico().isProduto()) {
				valorTotalProdutos = valorTotalProdutos.add(item.getValorUnitarioVenda().multiply(item.getQuantidade()));
			}
		}

		valorTotalProdutos = valorTotalProdutos.intValue() > 0
				? valorTotalProdutos.subtract(valorTotalProdutos.multiply(percentualDesconto.divide(BigDecimal.valueOf(100))))
				: valorTotalProdutos;
		this.valorLiquido = valorTotalProdutos;
	}

}