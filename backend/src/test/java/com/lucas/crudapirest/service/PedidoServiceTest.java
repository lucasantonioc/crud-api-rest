package com.lucas.crudapirest.service;

import com.lucas.crudapirest.dto.PedidoItemResponseDTO;
import com.lucas.crudapirest.dto.PedidoResponseDTO;
import com.lucas.crudapirest.entity.Pedido;
import com.lucas.crudapirest.entity.PedidoItem;
import com.lucas.crudapirest.entity.ProdutoServico;
import com.lucas.crudapirest.enumeration.EnumStatusPedido;
import com.lucas.crudapirest.enumeration.EnumStatusProdutoServico;
import com.lucas.crudapirest.enumeration.EnumTipoProdutoServico;
import com.lucas.crudapirest.exception.BusinessException;
import com.lucas.crudapirest.mapper.PedidoItemMapper;
import com.lucas.crudapirest.mapper.PedidoMapper;
import com.lucas.crudapirest.mapper.ProdutoServicoMapper;
import com.lucas.crudapirest.service.impl.PedidoItemServiceImpl;
import com.lucas.crudapirest.service.impl.PedidoServiceImpl;
import com.lucas.crudapirest.service.impl.ProdutoServicoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Autowired
    private PedidoItemServiceImpl pedidoItemService;

    @Autowired
    private ProdutoServicoServiceImpl produtoServicoService;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoItemMapper pedidoItemMapper;

    @Autowired
    private ProdutoServicoMapper produtoServicoMapper;

    @Test
    void testCadastrarNovoPedido() {
        Assertions.assertNotNull(pedidoService.create(pedidoMapper.toPersistenceDTO(buildPedido())));
    }

    //tentar dar desconto num pedido fechado

    @Test
    void testAddItemNumPedidoFechado() {
        PedidoComItem result = getPedidoComItem();
        Assertions.assertNotNull(result.pedidoSalvo);
        Assertions.assertNotNull(result.itemSalvo);

        var pedido = pedidoService.findById(result.pedidoSalvo.getId());
        pedido.alterarStatus();
        pedidoService.update(pedido);

        var item = buildPedidoItem();
        item.setProdutoServico(getProduto());

        Assertions.assertThrows(BusinessException.class,
                () -> pedidoItemService.create(result.pedidoSalvo.getId(), pedidoItemMapper.toPersistDTO(item)));
    }

    @Test
    void testDeletarProdutoUtilizado() {
        PedidoComItem result = getPedidoComItem();
        Assertions.assertNotNull(result.pedidoSalvo);
        Assertions.assertNotNull(result.itemSalvo);

        var item = pedidoItemService.findById(result.itemSalvo.getId());
        Assertions.assertThrows(RuntimeException.class,
                () -> produtoServicoService.delete(item.getProdutoServico().getId()));
    }

    @Test
    void testAplicarDescontoSobreItensDoTipoProduto() {
        PedidoComItem result = getPedidoComItem();
        Assertions.assertNotNull(result.pedidoSalvo);
        Assertions.assertNotNull(result.itemSalvo);

        var servico = getServico();
        var item = buildPedidoItem();
        item.setProdutoServico(servico);

        var pedidoId = result.pedidoSalvo().getId();
        pedidoItemService.create(pedidoId, pedidoItemMapper.toPersistDTO(item));

        Assertions.assertEquals(BigDecimal.valueOf(45.00).setScale(2),
                pedidoService.findById(pedidoId).getValorLiquido());

        var produto = getProduto();
        produto.setTipo(EnumTipoProdutoServico.PRODUTO);
        produtoServicoService.update(servico.getId(), produtoServicoMapper.toUpdateDTO(produto));

        item.setProdutoServico(produto);
        pedidoItemService.create(pedidoId, pedidoItemMapper.toPersistDTO(item));
        Assertions.assertEquals(BigDecimal.valueOf(90.00).setScale(2),
                pedidoService.findById(pedidoId).getValorLiquido());
    }

    @Test
    void testValidacaoAddProdutoInativoNoPedido() {
        PedidoComItem result = getPedidoComItem();
        Assertions.assertNotNull(result.pedidoSalvo);
        Assertions.assertNotNull(result.itemSalvo);

        var produto = getProduto();
        produto.setStatus(EnumStatusProdutoServico.INATIVO);
        produtoServicoService.alterarStatus(produto.getId());
        Assertions.assertFalse(produto.isAtivo());

        var item = buildPedidoItem();
        item.setProdutoServico(produto);
        Assertions.assertThrows(BusinessException.class,
                () -> pedidoItemService.create(result.pedidoSalvo.getId(), pedidoItemMapper.toPersistDTO(item)));
    }

    private ProdutoServico getServico() {
        var servico = buildProdutoServico();
        servico.setTipo(EnumTipoProdutoServico.SERVICO);
        var id = produtoServicoService.create(produtoServicoMapper.toPersistDTO(servico)).getId();
        servico.setId(id);
        return servico;
    }

    private ProdutoServico getProduto() {
        var produto = buildProdutoServico();
        var id = produtoServicoService.create(produtoServicoMapper.toPersistDTO(produto)).getId();
        produto.setId(id);
        return produto;
    }

    private Pedido buildPedido() {
        Pedido pedido = Pedido.builder()
                .status(EnumStatusPedido.ABERTO)
                .observacao("Pedido de teste 1")
                .valorBruto(BigDecimal.ZERO)
                .valorLiquido(BigDecimal.ZERO)
                .percentualDesconto(BigDecimal.ZERO)
                .build();
        return pedido;
    }

    private PedidoItem buildPedidoItem() {
        return PedidoItem.builder()
                .quantidade(BigDecimal.valueOf(1))
                .valorUnitarioVenda(BigDecimal.valueOf(50))
                .build();
    }

    private ProdutoServico buildProdutoServico() {
        return ProdutoServico.builder()
                .status(EnumStatusProdutoServico.ATIVO)
                .tipo(EnumTipoProdutoServico.PRODUTO)
                .descricao("MOUSE SEM FIO LOGITEC")
                .valorUnitario(BigDecimal.valueOf(69.90))
                .build();
    }

    private PedidoComItem getPedidoComItem() {
        var pedidoDto = buildPedido();
        pedidoDto.setPercentualDesconto(BigDecimal.TEN);
        var pedidoSalvo = pedidoService.create(pedidoMapper.toPersistenceDTO(pedidoDto));

        var produtoServicoSalvo = produtoServicoService.create(produtoServicoMapper.toPersistDTO(buildProdutoServico()));
        var item = buildPedidoItem();
        item.setProdutoServico(produtoServicoMapper.toEntity(produtoServicoSalvo));

        var itemSalvo = pedidoItemService.create(pedidoSalvo.getId(), pedidoItemMapper.toPersistDTO(item));
        PedidoComItem result = new PedidoComItem(pedidoSalvo, itemSalvo);
        return result;
    }

    private record PedidoComItem(PedidoResponseDTO pedidoSalvo, PedidoItemResponseDTO itemSalvo) {
    }

}
