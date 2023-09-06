package com.lucas.crudapirest.service;

import com.lucas.crudapirest.dto.PedidoPersistDTO;
import com.lucas.crudapirest.dto.PedidoResponseDTO;
import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PedidoService {

	PedidoResponseDTO create(PedidoPersistDTO pedidoPersistDTO);
	Pedido findById(UUID id);
	PedidoResponseDTO findByIdDTO(UUID id);
	void delete(UUID id);
	PedidoResponseDTO update(Pedido pedido);

	PedidoResponseDTO alterarStatus(UUID pedidoId);

	Page<PedidoResponseDTO> findAll(Pageable pageable, PedidoFilterDTO filterDTO);

	void atualizarTotaisPedido(UUID pedidoId);

}