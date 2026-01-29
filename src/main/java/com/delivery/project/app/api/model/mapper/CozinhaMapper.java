package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.domain.model.Cozinha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CozinhaMapper {
    
    CozinhaDto toModel(Cozinha cozinha);
    Cozinha toEntity(CozinhaDto cozinhaDto);
    List<CozinhaDto> toCollectionModel(List<Cozinha> cozinhas);
    
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Cozinha cozinha, CozinhaDto dto);
}
