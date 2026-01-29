package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.domain.model.Permissao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissaoMapper {
    
    PermissaoDto toModel(Permissao permissao);
    List<PermissaoDto> toCollectionModel(List<Permissao> permissoes);
    Permissao toEntity(PermissaoDto permissaoDto);
}
