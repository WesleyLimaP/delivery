package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.mapper.utils.EstadoMap;
import com.delivery.project.app.domain.exceptions.EstadoNaoEncontradoException;
import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.domain.repository.EstadoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EstadoMap.class})
public interface CidadeMapper {
    
    @Mapping(target = "estado", source = "cidade.estado.id")
    Cidade toEntity(CidadeRequestDto cidade);

    void update(@MappingTarget Cidade cidade, CidadeUpdateDto cidadeDto);
    
    CidadeDto toModel(Cidade cidade);
    List<CidadeDto> toCollectionModel(List<Cidade> cidades);

}

