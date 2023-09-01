package com.lucas.crudapirest.service.impl;

import com.lucas.crudapirest.exception.BusinessException;
import com.lucas.crudapirest.exception.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.lucas.crudapirest.service.ProdutoServicoService;
import com.lucas.crudapirest.repository.ProdutoServicoRepository;
import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import java.util.UUID;

@Service
public class ProdutoServicoServiceImpl implements ProdutoServicoService {

	@Autowired
	private ProdutoServicoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProdutoServicoResponseDTO create(ProdutoServicoPersistDTO produtoServicoPersistDTO) {
		ProdutoServico produtoServico = modelMapper.map(produtoServicoPersistDTO, ProdutoServico.class);
		return toResponseDTO(repository.save(produtoServico));
	}

	@Override
	public ProdutoServico findById(UUID id) {
		return repository.findById(id)
			.orElseThrow(() -> new RecordNotFoundException(id));
	}

	@Override
	public ProdutoServicoResponseDTO findByIdDTO(UUID id) {
		return toResponseDTO(findById(id));
	}

	@Override
	public void delete(UUID id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException("Produto/Serviço já utilizado");
		}
	}

	@Override
	public ProdutoServicoResponseDTO update(ProdutoServicoUpdateDTO produtoServicoUpdateDTO) {
		ProdutoServico produtoServico = findById(produtoServicoUpdateDTO.getId());
		produtoServico.update(produtoServicoUpdateDTO);
		return toResponseDTO(repository.save(produtoServico));
	}

	@Override
	public List<ProdutoServicoResponseDTO> findAll() {
		return repository.findAll()
			.stream()
			.map(this::toResponseDTO)
			.collect(Collectors.toList());
	}

	private ProdutoServicoResponseDTO toResponseDTO(ProdutoServico produtoServico) {
		return modelMapper.map(produtoServico, ProdutoServicoResponseDTO.class);
	}

}