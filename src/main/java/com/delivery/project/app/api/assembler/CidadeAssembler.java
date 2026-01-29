package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CidadeController;
import com.delivery.project.app.api.controller.EstadoController;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.mapper.CidadeMapper;
import com.delivery.project.app.domain.model.Cidade;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CidadeAssembler implements RepresentationModelAssembler<Cidade, CidadeDto> {
    @Autowired
    private CidadeMapper cidadeMapper;


    @Override
    public CidadeDto toModel(Cidade entity) {
        var dto = cidadeMapper.toModel(entity);
        addLinks(dto);
        return dto;

    }
    public Cidade toEntity(CidadeRequestDto dto) {
        return cidadeMapper.toEntity(dto);
    }
    public void update(@MappingTarget Cidade cidade, CidadeUpdateDto dto){
        cidadeMapper.update(cidade, dto);
    }

    @Override
    public CollectionModel<CidadeDto> toCollectionModel(Iterable<? extends Cidade> entities) {
        var collection =  RepresentationModelAssembler.super.toCollectionModel(entities);
        collection.add(linkTo(methodOn(CidadeController.class)
                .findAll())
                .withSelfRel());
        return collection;
    }

    private void addLinks(@MappingTarget CidadeDto cidadeDto) {
        cidadeDto.add(linkTo(methodOn(CidadeController.class)
                .findById(cidadeDto.getId()))
                .withSelfRel());
        cidadeDto.getEstado().add(linkTo(methodOn(EstadoController.class)
                .findById(cidadeDto.getEstado().getId()))
                .withSelfRel());
        cidadeDto.add(linkTo(methodOn(CidadeController.class)
                .findAll())
                .withRel("cidades"));
    }

}
