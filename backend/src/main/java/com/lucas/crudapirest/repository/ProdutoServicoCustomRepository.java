package com.lucas.crudapirest.repository;

import com.lucas.crudapirest.dto.filter.ProdutoServicoFilterDTO;
import com.lucas.crudapirest.entity.ProdutoServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoServicoCustomRepository {

    Page<ProdutoServico> findAll(Pageable pageable, ProdutoServicoFilterDTO filter);

}
