package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.FormaDePagamentoController;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoDescricaoDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.api.model.mapper.FormaDePagamentoMapper;
import com.delivery.project.app.domain.model.FormaDePagamento;
import org.mapstruct.AfterMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FormaDePagamentoAssembler implements RepresentationModelAssembler<FormaDePagamento, FormaDePagamentoResponseDto> {
    
    @Autowired
    private FormaDePagamentoMapper formaDePagamentoMapper;
    
    @Override
    public FormaDePagamentoResponseDto toModel(FormaDePagamento entity) {
        var model = formaDePagamentoMapper.toModel(entity);
        addLinks(model);
        return model;
    }
    public FormaDePagamento toEntity(FormaDePagamentoDescricaoDto dto){
        return formaDePagamentoMapper.toEntity(dto);
    }
    
    @Override
    public CollectionModel<FormaDePagamentoResponseDto> toCollectionModel(Iterable<? extends FormaDePagamento> entities) {
        var collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(FormaDePagamentoController.class)
                .findAll())
                .withSelfRel());
        return collection;
    }



    public void update(FormaDePagamento formaDePagamento, FormaDePagamentoDescricaoDto dto) {
        formaDePagamentoMapper.update(formaDePagamento, dto);
    }

    private void addLinks(FormaDePagamentoResponseDto formaDePagamentoResponseDto) {
        formaDePagamentoResponseDto.add(linkTo(methodOn(FormaDePagamentoController.class)
                .findById(formaDePagamentoResponseDto.getId()))
                .withSelfRel());
        formaDePagamentoResponseDto.add(linkTo(methodOn(FormaDePagamentoController.class)
                .findAll())
                .withRel("formas-de-pagamento"));
    }
}
