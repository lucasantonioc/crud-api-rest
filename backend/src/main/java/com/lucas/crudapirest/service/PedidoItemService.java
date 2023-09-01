package com.lucas.crudapirest.service;

import com.lucas.crudapirest.dto.PedidoItemPersistDTO;
import com.lucas.crudapirest.dto.PedidoItemResponseDTO;
import com.lucas.crudapirest.dto.PedidoItemUpdateDTO;
import com.lucas.crudapirest.entity.PedidoItem;
import java.util.List;
import java.util.UUID;

public interface PedidoItemService {

	PedidoItemResponseDTO create(UUID pedidoId, PedidoItemPersistDTO pedidoItemPersistDTO);
	PedidoItem findById(UUID id);
	PedidoItemResponseDTO findByIdDTO(UUID id);

	List<PedidoItemResponseDTO> findAllByPedido(UUID pedidoId);
	void delete(UUID id);
	PedidoItemResponseDTO update(PedidoItemUpdateDTO pedidoItemUpdateDTO);
	List<PedidoItemResponseDTO> findAll();
}