package com.lucas.crudapirest.service.impl;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.entity.PedidoItem;
import com.lucas.crudapirest.exception.BusinessException;
import com.lucas.crudapirest.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.lucas.crudapirest.service.PedidoService;
import com.lucas.crudapirest.repository.PedidoRepository;
import com.lucas.crudapirest.dto.PedidoPersistDTO;
import com.lucas.crudapirest.dto.PedidoResponseDTO;
import com.lucas.crudapirest.dto.PedidoUpdateDTO;
import com.lucas.crudapirest.entity.Pedido;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	private void validate(Pedido pedido) {
		if (!pedido.isAberto()) {
			throw new BusinessException("Pedido já está fechado");
		}
	}

	@Override
	public void atualizarTotaisPedido(UUID pedidoId) {
		var pedido = repository.findById(pedidoId).orElseThrow(() -> new RecordNotFoundException(pedidoId));
		pedido.recalcularTotaisPedido();
		repository.save(pedido);
	}

	@Override
	public PedidoResponseDTO create(PedidoPersistDTO pedidoPersistDTO) {
		Pedido pedido = modelMapper.map(pedidoPersistDTO, Pedido.class);
		return toResponseDTO(repository.save(pedido));
	}

	@Override
	public Pedido findById(UUID id) {
		return repository.findById(id)
			.orElseThrow(() -> new RecordNotFoundException(id));
	}

	@Override
	public PedidoResponseDTO findByIdDTO(UUID id) {
		return toResponseDTO(findById(id));
	}

	@Override
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public PedidoResponseDTO update(Pedido pedido) {
		validate(pedido);
		return toResponseDTO(repository.save(pedido));
	}

	@Transactional
	@Override
	public PedidoResponseDTO fechar(UUID pedidoId) {
		var pedido = repository.findById(pedidoId).orElseThrow(() -> new RecordNotFoundException(pedidoId));
		pedido.fechar();
		return toResponseDTO(repository.save(pedido));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<PedidoResponseDTO> findAll(Pageable pageable, PedidoFilterDTO filter) {
		return repository.findAll(pageable, filter)
			.map(this::toResponseDTO);
	}

	private PedidoResponseDTO toResponseDTO(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResponseDTO.class);
	}

}