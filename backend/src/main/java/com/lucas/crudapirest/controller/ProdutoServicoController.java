package com.lucas.crudapirest.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.lucas.crudapirest.service.ProdutoServicoService;
import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class ProdutoServicoController {

	@Autowired
	private ProdutoServicoService service;

	@PostMapping("produtos-servicos")
	public ResponseEntity<ProdutoServicoResponseDTO> create(@RequestBody ProdutoServicoPersistDTO produtoServicoPersistDTO) {
		return ResponseEntity.ok(service.create(produtoServicoPersistDTO));
	}

	@GetMapping("produtos-servicos/{id}")
	public ResponseEntity<ProdutoServicoResponseDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findByIdDTO(id));
	}

	@DeleteMapping("produtos-servicos/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("produtos-servicos")
	public ResponseEntity<ProdutoServicoResponseDTO> update(@RequestBody ProdutoServicoUpdateDTO produtoServicoUpdateDTO) {
		return ResponseEntity.ok(service.update(produtoServicoUpdateDTO));
	}

	@GetMapping("produtos-servicos")
	public ResponseEntity<List<ProdutoServicoResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

}