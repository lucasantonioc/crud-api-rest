package com.lucas.crudapirest.service.impl;

import com.lucas.crudapirest.exception.BusinessException;
import com.lucas.crudapirest.exception.RecordNotFoundException;
import com.lucas.crudapirest.repository.ProdutoServicoRepository;
import com.lucas.crudapirest.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.lucas.crudapirest.service.PedidoItemService;
import com.lucas.crudapirest.repository.PedidoItemRepository;
import com.lucas.crudapirest.dto.PedidoItemPersistDTO;
import com.lucas.crudapirest.dto.PedidoItemResponseDTO;
import com.lucas.crudapirest.dto.PedidoItemUpdateDTO;
import com.lucas.crudapirest.entity.PedidoItem;
import java.util.UUID;

@Service
public class PedidoItemServiceImpl implements PedidoItemService {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoItemRepository repository;

	@Autowired
	private ProdutoServicoRepository produtoServicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	private void validate(PedidoItemPersistDTO pedidoItemPersistDTO) {
		var produtoServico = produtoServicoRepository.findById(pedidoItemPersistDTO.getProdutoServicoId())
				.orElseThrow(() -> new RecordNotFoundException(pedidoItemPersistDTO.getProdutoServicoId()));

		if (!produtoServico.isAtivo()) {
			throw new BusinessException("Produto/Serviço não está ativo");
		}
	}

	@Override
	public PedidoItemResponseDTO create(UUID pedidoId, PedidoItemPersistDTO pedidoItemPersistDTO) {
		validate(pedidoItemPersistDTO);
		pedidoItemPersistDTO.setPedidoId(pedidoId);
		PedidoItem pedidoItem = modelMapper.map(pedidoItemPersistDTO, PedidoItem.class);

		var pedidoItemSaved = repository.save(pedidoItem);
		pedidoService.atualizarTotaisPedido(pedidoId);

		return toResponseDTO(pedidoItemSaved);
	}

	@Override
	public PedidoItem findById(UUID id) {
		return repository.findById(id)
			.orElseThrow(() -> new RecordNotFoundException(id));
	}

	@Override
	public PedidoItemResponseDTO findByIdDTO(UUID id) {
		return toResponseDTO(findById(id));
	}

    @Override
    public List<PedidoItemResponseDTO> findAllByPedido(UUID pedidoId) {
        return repository.findAllByPedidoId(pedidoId)
				.stream()
				.map(this::toResponseDTO)
				.collect(Collectors.toList());
    }

    @Override
	public void delete(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public PedidoItemResponseDTO update(PedidoItemUpdateDTO pedidoItemUpdateDTO) {
		PedidoItem pedidoItem = findById(pedidoItemUpdateDTO.getId());
		pedidoItem.update(pedidoItemUpdateDTO);
		return toResponseDTO(repository.save(pedidoItem));
	}

	@Override
	public List<PedidoItemResponseDTO> findAll() {
		return repository.findAll()
			.stream()
			.map(this::toResponseDTO)
			.collect(Collectors.toList());
	}

	private PedidoItemResponseDTO toResponseDTO(PedidoItem pedidoItem) {
		return modelMapper.map(pedidoItem, PedidoItemResponseDTO.class);
	}

}