package com.lucas.crudapirest.service;

import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import java.util.List;
import java.util.UUID;

public interface ProdutoServicoService {

	ProdutoServicoResponseDTO create(ProdutoServicoPersistDTO produtoServicoPersistDTO);
	ProdutoServico findById(UUID id);
	ProdutoServicoResponseDTO findByIdDTO(UUID id);
	void delete(UUID id);
	ProdutoServicoResponseDTO update(ProdutoServicoUpdateDTO produtoServicoUpdateDTO);
	List<ProdutoServicoResponseDTO> findAll();
}