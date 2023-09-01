package com.lucas.crudapirest.controller;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.lucas.crudapirest.service.PedidoService;
import com.lucas.crudapirest.dto.PedidoPersistDTO;
import com.lucas.crudapirest.dto.PedidoResponseDTO;
import com.lucas.crudapirest.dto.PedidoUpdateDTO;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping
	public ResponseEntity<PedidoResponseDTO> create(@RequestBody @Valid PedidoPersistDTO pedidoPersistDTO) {
		return ResponseEntity.ok(service.create(pedidoPersistDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findByIdDTO(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PedidoUpdateDTO pedidoUpdateDTO) {
		var pedido = service.findById(id);
		pedido.update(pedidoUpdateDTO);
		return ResponseEntity.ok(service.update(pedido));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<PedidoResponseDTO> fechar(@PathVariable UUID id) {
		return ResponseEntity.ok(service.fechar(id));
	}

	@GetMapping
	public Page<PedidoResponseDTO> findAll(Pageable pageable, @RequestBody @Valid PedidoFilterDTO pedidoFilterDTO) {
		return service.findAll(pageable, pedidoFilterDTO);
	}

}