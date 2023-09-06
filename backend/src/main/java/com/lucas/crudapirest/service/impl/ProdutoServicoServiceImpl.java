package com.lucas.crudapirest.service.impl;

import com.lucas.crudapirest.dto.filter.ProdutoServicoFilterDTO;
import com.lucas.crudapirest.exception.BusinessException;
import com.lucas.crudapirest.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.modelmapper.ModelMapper;
import com.lucas.crudapirest.service.ProdutoServicoService;
import com.lucas.crudapirest.repository.ProdutoServicoRepository;
import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@Service
public class ProdutoServicoServiceImpl implements ProdutoServicoService {

	@Autowired
	private ProdutoServicoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public ProdutoServicoResponseDTO create(ProdutoServicoPersistDTO produtoServicoPersistDTO) {
		ProdutoServico produtoServico = modelMapper.map(produtoServicoPersistDTO, ProdutoServico.class);
		return toResponseDTO(repository.save(produtoServico));
	}

	@Transactional(readOnly = true)
	@Override
	public ProdutoServico findById(UUID id) {
		return repository.findById(id)
			.orElseThrow(() -> new RecordNotFoundException(id));
	}

	@Transactional
	@Override
	public ProdutoServicoResponseDTO findByIdDTO(UUID id) {
		return toResponseDTO(findById(id));
	}

	@Transactional
	@Override
	public void delete(UUID id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException("Produto/Serviço já utilizado");
		}
	}

	@Transactional
	@Override
	public ProdutoServicoResponseDTO update(UUID id, ProdutoServicoUpdateDTO produtoServicoUpdateDTO) {
		var produtoServico = findById(id);
		produtoServico.update(produtoServicoUpdateDTO);
		return toResponseDTO(repository.save(produtoServico));
	}

	public ProdutoServicoResponseDTO alterarStatus(UUID id) {
		var produtoServico = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
		produtoServico.alterarStatus();
		return toResponseDTO(repository.save(produtoServico));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<ProdutoServicoResponseDTO> findAll(Pageable pageable, ProdutoServicoFilterDTO filter) {
		return repository.findAll(pageable, filter)
			.map(this::toResponseDTO);
	}

	private ProdutoServicoResponseDTO toResponseDTO(ProdutoServico produtoServico) {
		return modelMapper.map(produtoServico, ProdutoServicoResponseDTO.class);
	}

}