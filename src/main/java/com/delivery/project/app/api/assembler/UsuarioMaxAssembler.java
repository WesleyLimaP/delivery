package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.UsuarioController;
import com.delivery.project.app.api.model.dto.usuarioDto.request.GrupoUsuarioRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioPostRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.request.UsuarioUpdateRequestDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMaxResponse;
import com.delivery.project.app.domain.exceptions.GrupoNaoEncontradoException;
import com.delivery.project.app.domain.model.Grupo;
import com.delivery.project.app.domain.model.Usuario;
import com.delivery.project.app.domain.repository.GrupoRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", uses = {GrupoMap.class})
public interface UsuarioMaxAssembler extends RepresentationModelAssembler<Usuario, UsuarioMaxResponse> {
    @Override
    UsuarioMaxResponse toModel(Usuario usuario);
    List<UsuarioMaxResponse> toCollectionModel(List<Usuario> usuarios);
    void update(@MappingTarget Usuario usuario, UsuarioUpdateRequestDto dto);
    @Mapping(target = "grupos", source = "grupos")
    Usuario toEntity(UsuarioPostRequestDto dto);

    @AfterMapping
    default void addLinks(@MappingTarget UsuarioMaxResponse usuarioMaxResponse){
        usuarioMaxResponse.add(linkTo(methodOn(UsuarioController.class).findById(usuarioMaxResponse.getId())).withSelfRel());
    }
}
@Component
class GrupoMap {
    @Autowired
    private GrupoRepository repository;

    public Grupo fromId(GrupoUsuarioRequestDto grupo) {
        return repository.findById(grupo.id())
                .orElseThrow(() -> new GrupoNaoEncontradoException(" nao foi possivel encontrar o grupo com id informado"));
    }
}
