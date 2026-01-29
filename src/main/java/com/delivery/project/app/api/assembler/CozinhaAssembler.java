package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CozinhaController;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.api.model.mapper.CozinhaMapper;
import com.delivery.project.app.domain.model.Cozinha;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CozinhaAssembler implements RepresentationModelAssembler<Cozinha, CozinhaDto> {
    
    @Autowired
    private CozinhaMapper cozinhaMapper;
    
    @Override
    public CozinhaDto toModel(Cozinha entity) {
        var cozinhaDto = cozinhaMapper.toModel(entity);
        addLinks(cozinhaDto);
        return cozinhaDto;
    }

    public Cozinha toEntity(CozinhaDto dto) {
        return cozinhaMapper.toEntity(dto);
    }

    public void update(@MappingTarget Cozinha cozinha, CozinhaDto dto){
        cozinhaMapper.update(cozinha, dto);
    }

    @Override
    public CollectionModel<CozinhaDto> toCollectionModel(Iterable<? extends Cozinha> entities) {
        var collection =  RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(CozinhaController.class)
                .findAll())
                .withSelfRel());
        return collection;
    }
    
    private void addLinks(CozinhaDto cozinhaDto) {
        cozinhaDto
                .add(linkTo(methodOn(CozinhaController.class)
                        .findById(cozinhaDto.getId()))
                        .withSelfRel());
    }
}
