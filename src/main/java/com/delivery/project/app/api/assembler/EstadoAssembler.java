package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CidadeController;
import com.delivery.project.app.api.controller.EstadoController;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.model.Estado;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface EstadoAssembler extends RepresentationModelAssembler<Estado, EstadoDto> {

    @Override
    EstadoDto toModel(Estado estado);
    Estado toEntity(EstadoDto estado);
    List<EstadoDto> toCollectionModel(List<Estado> estado);
    void update(@MappingTarget Estado estado, EstadoNomeDto dto);
    @AfterMapping
    default void addLinks(@MappingTarget EstadoDto estadoDto) {
        estadoDto.add(linkTo(methodOn(EstadoController.class)
                .findById(estadoDto.getId()))
                .withSelfRel());
    }
}
