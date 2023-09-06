package com.lucas.crudapirest.mapper;

import com.lucas.crudapirest.dto.ProdutoServicoPersistDTO;
import com.lucas.crudapirest.dto.ProdutoServicoResponseDTO;
import com.lucas.crudapirest.dto.ProdutoServicoUpdateDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoServicoMapper {

    private ModelMapper modelMapper;

    @Autowired
    public ProdutoServicoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoServicoResponseDTO toResponseDTO(ProdutoServico produtoServico) {
        return modelMapper.map(produtoServico, ProdutoServicoResponseDTO.class);
    }

    public ProdutoServicoPersistDTO toPersistDTO(ProdutoServico produtoServico) {
        return modelMapper.map(produtoServico, ProdutoServicoPersistDTO.class);
    }

    public ProdutoServicoUpdateDTO toUpdateDTO(ProdutoServico produtoServico) {
        return modelMapper.map(produtoServico, ProdutoServicoUpdateDTO.class);
    }

    public ProdutoServico toEntity(ProdutoServicoPersistDTO pedidoPersistDTO) {
        return modelMapper.map(pedidoPersistDTO, ProdutoServico.class);
    }

    public ProdutoServico toEntity(ProdutoServicoResponseDTO pedidoResponseDTO) {
        return modelMapper.map(pedidoResponseDTO, ProdutoServico.class);
    }


}
