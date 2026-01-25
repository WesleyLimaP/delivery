package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.GrupoPermissaoController;
import com.delivery.project.app.api.model.dto.permissaoDto.PermissaoDto;
import com.delivery.project.app.domain.model.Permissao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface PermissaoAssembler extends RepresentationModelAssembler<Permissao, PermissaoDto> {
    @Override
    PermissaoDto toModel(Permissao permissao);
    List<PermissaoDto> toCollectionModel(List<Permissao> permissoes);
    void update(@MappingTarget Permissao permissao, PermissaoDto permissaoRequestDto);
    Permissao toEntity(PermissaoDto permissaoRequestDto);

    @AfterMapping
    default void addLinks(@MappingTarget PermissaoDto permissaoDto ) {
        permissaoDto.add(WebMvcLinkBuilder.linkTo(methodOn(GrupoPermissaoController.class)
                        .findAllPermissoes(permissaoDto.getId()))
                .withSelfRel());
    }
}
