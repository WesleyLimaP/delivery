package com.delivery.project.app.domain.service;

import com.delivery.project.app.api.controller.UsuarioController;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.Usuario;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface UsuarioMinAssembler extends RepresentationModelAssembler<Usuario, UsuarioMinResponse> {
    @Override
    UsuarioMinResponse toModel(Usuario usuario);
    List<UsuarioMinResponse> toCollectionModel(List<Usuario> usuarios);
    void update(@MappingTarget Usuario usuario, UsuarioMinResponse usuarioMinResponse);
    Usuario toEntity(UsuarioMinResponse usuarioMinResponse);

    @AfterMapping
    default void addLinks(@MappingTarget UsuarioMinResponse usuarioMinResponse){
        usuarioMinResponse.add(linkTo(methodOn(UsuarioController.class).findById(usuarioMinResponse.getId())).withSelfRel());
    }

}
