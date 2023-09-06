package com.lucas.crudapirest.controller;

import com.lucas.crudapirest.dto.filter.PedidoItemFilterDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.lucas.crudapirest.service.PedidoItemService;
import com.lucas.crudapirest.dto.PedidoItemPersistDTO;
import com.lucas.crudapirest.dto.PedidoItemResponseDTO;
import com.lucas.crudapirest.dto.PedidoItemUpdateDTO;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class PedidoItemController {

	private final PedidoItemService service;

	@Autowired
	public PedidoItemController(PedidoItemService service) {
		this.service = service;
	}

	@PostMapping("pedidos/{pedidoId}/itens")
	public ResponseEntity<PedidoItemResponseDTO> create(@PathVariable UUID pedidoId, @RequestBody @Valid PedidoItemPersistDTO pedidoItemPersistDTO) {
		return ResponseEntity.ok(service.create(pedidoId, pedidoItemPersistDTO));
	}

	@GetMapping("itens/{id}")
	public ResponseEntity<PedidoItemResponseDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findByIdDTO(id));
	}

	@GetMapping("pedidos/{pedidoId}/itens")
	public ResponseEntity<List<PedidoItemResponseDTO>> findAllByPedido(@PathVariable UUID pedidoId) {
		return ResponseEntity.ok(service.findAllByPedido(pedidoId));
	}

	@DeleteMapping("itens/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("itens")
	public ResponseEntity<PedidoItemResponseDTO> update(@RequestBody @Valid PedidoItemUpdateDTO pedidoItemUpdateDTO) {
		return ResponseEntity.ok(service.update(pedidoItemUpdateDTO));
	}

	@GetMapping("itens")
	public ResponseEntity<Page<PedidoItemResponseDTO>> findAll(Pageable pageable, @RequestBody @Valid PedidoItemFilterDTO filter) {
		return ResponseEntity.ok(service.findAll(pageable, filter));
	}

}