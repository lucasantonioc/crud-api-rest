package com.lucas.crudapirest.mapper;

import com.lucas.crudapirest.dto.PedidoItemPersistDTO;
import com.lucas.crudapirest.dto.PedidoItemResponseDTO;
import com.lucas.crudapirest.entity.PedidoItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoItemMapper {

    private ModelMapper modelMapper;

    @Autowired
    public PedidoItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PedidoItemResponseDTO toResponseDTO(PedidoItem item) {
        return modelMapper.map(item, PedidoItemResponseDTO.class);
    }

    public PedidoItemPersistDTO toPersistDTO(PedidoItem item) {
        return modelMapper.map(item, PedidoItemPersistDTO.class);
    }

    public PedidoItem toEntity(PedidoItemPersistDTO pedidoItemPersistDTO) {
        return modelMapper.map(pedidoItemPersistDTO, PedidoItem.class);
    }

}
