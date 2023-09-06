package com.lucas.crudapirest.service;

import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import com.lucas.crudapirest.dto.filter.ProdutoServicoFilterDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProdutoServicoService {

	ProdutoServicoResponseDTO create(ProdutoServicoPersistDTO produtoServicoPersistDTO);
	ProdutoServico findById(UUID id);
	ProdutoServicoResponseDTO findByIdDTO(UUID id);
	void delete(UUID id);
	ProdutoServicoResponseDTO update(UUID id, ProdutoServicoUpdateDTO produtoServicoUpdateDTO);

	ProdutoServicoResponseDTO alterarStatus(UUID id);
	Page<ProdutoServicoResponseDTO> findAll(Pageable pageable, ProdutoServicoFilterDTO filter);
}