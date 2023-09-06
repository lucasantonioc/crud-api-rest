package com.lucas.crudapirest.mapper;

import com.lucas.crudapirest.dto.PedidoPersistDTO;
import com.lucas.crudapirest.dto.PedidoResponseDTO;
import com.lucas.crudapirest.entity.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    private ModelMapper modelMapper;

    @Autowired
    public PedidoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoResponseDTO toResponseDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResponseDTO.class);
    }

    public PedidoPersistDTO toPersistenceDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoPersistDTO.class);
    }

    public Pedido toEntity(PedidoPersistDTO pedidoPersistDTO) {
        return modelMapper.map(pedidoPersistDTO, Pedido.class);
    }

}
