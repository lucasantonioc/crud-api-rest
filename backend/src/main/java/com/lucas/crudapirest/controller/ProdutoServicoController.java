package com.lucas.crudapirest.controller;

import com.lucas.crudapirest.dto.filter.ProdutoServicoFilterDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.lucas.crudapirest.service.ProdutoServicoService;
import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/produtos-servicos/")
public class ProdutoServicoController {

	private final ProdutoServicoService service;

	@Autowired
	public ProdutoServicoController(ProdutoServicoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ProdutoServicoResponseDTO> create(@RequestBody @Valid ProdutoServicoPersistDTO produtoServicoPersistDTO) {
		return ResponseEntity.ok(service.create(produtoServicoPersistDTO));
	}

	@GetMapping("{id}")
	public ResponseEntity<ProdutoServicoResponseDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findByIdDTO(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<ProdutoServicoResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ProdutoServicoUpdateDTO produtoServicoUpdateDTO) {
		return ResponseEntity.ok(service.update(id, produtoServicoUpdateDTO));
	}

	@PutMapping("{id}/status")
	public ResponseEntity<ProdutoServicoResponseDTO> alterarStatus(@PathVariable UUID id) {
		return ResponseEntity.ok(service.alterarStatus(id));
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoServicoResponseDTO>> findAll(Pageable pageable, @RequestBody @Valid ProdutoServicoFilterDTO filter) {
		return ResponseEntity.ok(service.findAll(pageable, filter));
	}

}