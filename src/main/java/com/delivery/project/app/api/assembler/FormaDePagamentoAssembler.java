package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.EstadoController;
import com.delivery.project.app.api.controller.FormaDePagamentoController;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.request.EstadoNomeDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoDescricaoDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.domain.model.FormaDePagamento;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface FormaDePagamentoAssembler extends RepresentationModelAssembler<FormaDePagamento, FormaDePagamentoResponseDto> {

    @Override
    FormaDePagamentoResponseDto toModel(FormaDePagamento formaDePagamento);
    FormaDePagamento toEntity(FormaDePagamentoResponseDto formaDePagamento);
    FormaDePagamento toEntity(FormaDePagamentoDescricaoDto formaDePagamento);
    List<FormaDePagamentoResponseDto> toCollectionModel(List<FormaDePagamento> formaDePagamento);
    void update(@MappingTarget FormaDePagamento formaDePagamento, FormaDePagamentoDescricaoDto formaDePagamentoResponseDto);

    @AfterMapping
    default void addLinks(@MappingTarget FormaDePagamentoResponseDto formaDePagamentoResponseDto) {
        formaDePagamentoResponseDto.add(linkTo(methodOn(FormaDePagamentoController.class)
                .findById(formaDePagamentoResponseDto.getId()))
                .withSelfRel());
    }
}
