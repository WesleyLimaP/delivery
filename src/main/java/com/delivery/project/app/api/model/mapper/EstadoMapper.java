package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import com.delivery.project.app.domain.model.Estado;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoMapper {
    
    EstadoDto toModel(Estado estado);
    Estado toEntity(EstadoDto estado);
    List<EstadoDto> toCollectionModel(List<Estado> estados);
    void update(@MappingTarget Estado estado, EstadoNomeDto dto);
}
