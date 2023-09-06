package com.lucas.crudapirest.service.impl;

import com.lucas.crudapirest.dto.filter.PedidoFilterDTO;
import com.lucas.crudapirest.exception.BusinessException;
import com.lucas.crudapirest.exception.RecordNotFoundException;
import com.lucas.crudapirest.mapper.PedidoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.crudapirest.service.PedidoService;
import com.lucas.crudapirest.repository.PedidoRepository;
import com.lucas.crudapirest.dto.PedidoPersistDTO;
import com.lucas.crudapirest.dto.PedidoResponseDTO;
import com.lucas.crudapirest.entity.Pedido;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PedidoServiceImpl implements PedidoService {

	private final PedidoMapper pedidoMapper;

	private final PedidoRepository repository;

	@Autowired
	public PedidoServiceImpl(PedidoRepository repository, PedidoMapper pedidoMapper) {
		this.repository = repository;
		this.pedidoMapper = pedidoMapper;
	}

	private void validate(Pedido pedidoUpdate) {
		if (!findById(pedidoUpdate.getId()).isAberto()) {
			throw new BusinessException("Pedido já está fechado");
		}
	}

	@Transactional
	@Override
	public void atualizarTotaisPedido(UUID pedidoId) {
		var pedido = repository.findById(pedidoId).orElseThrow(() -> new RecordNotFoundException(pedidoId));
		pedido.recalcularTotaisPedido();
		repository.save(pedido);
	}

	@Transactional
	@Override
	public PedidoResponseDTO create(PedidoPersistDTO pedidoPersistDTO) {
		Pedido pedido = pedidoMapper.toEntity(pedidoPersistDTO);
		return pedidoMapper.toResponseDTO(repository.save(pedido));
	}

	@Transactional(readOnly = true)
	@Override
	public Pedido findById(UUID id) {
		return repository.findById(id)
			.orElseThrow(() -> new RecordNotFoundException(id));
	}

	@Transactional(readOnly = true)
	@Override
	public PedidoResponseDTO findByIdDTO(UUID id) {
		return pedidoMapper.toResponseDTO(findById(id));
	}

	@Transactional
	@Override
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public PedidoResponseDTO update(Pedido pedido) {
		validate(pedido);
		return pedidoMapper.toResponseDTO(repository.save(pedido));
	}

	@Transactional
	@Override
	public PedidoResponseDTO alterarStatus(UUID pedidoId) {
		var pedido = repository.findById(pedidoId).orElseThrow(() -> new RecordNotFoundException(pedidoId));
		pedido.alterarStatus();
		return pedidoMapper.toResponseDTO(repository.save(pedido));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<PedidoResponseDTO> findAll(Pageable pageable, PedidoFilterDTO filter) {
		return repository.findAll(pageable, filter)
			.map(p -> pedidoMapper.toResponseDTO(p));
	}

}