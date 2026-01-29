package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.mapper.utils.GrupoMap;
import com.delivery.project.app.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GrupoMap.class})
public interface UsuarioMaxMapper {
    
    UsuarioMaxResponse toModel(Usuario usuario);
    List<UsuarioMaxResponse> toCollectionModel(List<Usuario> usuarios);
    
    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioPostRequestDto usuarioPostRequestDto);
    
    void update(@MappingTarget Usuario usuario, UsuarioUpdateRequestDto usuarioUpdateRequestDto);
    void update(@MappingTarget Usuario usuario, GrupoUsuarioRequestDto grupoUsuarioRequestDto);
}
