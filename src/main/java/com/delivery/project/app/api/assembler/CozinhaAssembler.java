package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CozinhaController;
import com.delivery.project.app.api.model.dto.cozinhaDto.CozinhaDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.model.Cozinha;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface CozinhaAssembler extends RepresentationModelAssembler<Cozinha, CozinhaDto> {

    @Override
    CozinhaDto toModel(Cozinha cozinha);
    Cozinha toEntity(CozinhaDto cozinhaDto);
    List<CozinhaDto> toCollectionModel(List<Cozinha> cozinha);
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Cozinha cozinha, CozinhaDto dto);

    @AfterMapping
    default void addLinks(@MappingTarget CozinhaDto cozinhaDto) {
        cozinhaDto
                .add(linkTo(methodOn(CozinhaController.class)
                        .findById(cozinhaDto.getId()))
                        .withSelfRel());
    }

}
