package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.UsuarioController;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.api.model.mapper.UsuarioMaxMapper;
import com.delivery.project.app.api.model.mapper.utils.GrupoMap;
import com.delivery.project.app.domain.model.Usuario;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioMaxAssembler implements RepresentationModelAssembler<Usuario, UsuarioMaxResponse> {
    @Autowired
    private UsuarioMaxMapper usuarioMaxMapper;

    public UsuarioMaxResponse toModel(Usuario usuario){
        var usuarioMaxResponse = usuarioMaxMapper.toModel(usuario);
        addLinks(usuarioMaxResponse);
        return usuarioMaxResponse;
    };

    @Override
    public CollectionModel<UsuarioMaxResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    public void update(@MappingTarget Usuario usuario, UsuarioUpdateRequestDto dto){
        usuarioMaxMapper.update(usuario, dto);
    };

    public Usuario toEntity(UsuarioPostRequestDto dto){
        return usuarioMaxMapper.toEntity(dto);
    };

    @AfterMapping
    void addLinks(@MappingTarget UsuarioMaxResponse usuarioMaxResponse){
        usuarioMaxResponse.add(linkTo(methodOn(UsuarioController.class).findById(usuarioMaxResponse.getId())).withSelfRel());
    }
}
