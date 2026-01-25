package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.CidadeController;
import com.delivery.project.app.api.controller.EstadoController;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeRequestDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeUpdateDto;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import com.delivery.project.app.api.model.dto.endereco.estadoDto.EstadoDto;
import com.delivery.project.app.domain.exceptions.EntidadeNaoEncontradaException;
import com.delivery.project.app.domain.exceptions.EstadoNaoEncontradoException;
import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.model.Estado;
import com.delivery.project.app.domain.repository.EstadoRepository;
import com.delivery.project.app.domain.service.EstadoService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring",
        uses = {EstadoEntity.class})
public interface CidadeAssembler extends RepresentationModelAssembler<Cidade, CidadeDto> {

    @Override
    CidadeDto toModel(Cidade cidade);
    @Mapping(target = "estado", source = "estado.id")
    Cidade toEntity(CidadeRequestDto cidadeDto);
    List<CidadeDto> toCollectionModel(List<Cidade> cidade);
    void update(@MappingTarget Cidade cidade, CidadeUpdateDto dto);

    @AfterMapping
    default void addLinks(@MappingTarget CidadeDto cidadeDto) {
        cidadeDto.add(linkTo(methodOn(CidadeController.class)
                .findById(cidadeDto.getId()))
                .withSelfRel());
        cidadeDto.getEstado().add(linkTo(methodOn(EstadoController.class)
                .findById(cidadeDto.getEstado().getId()))
                .withSelfRel());
    }
}
@Component
class EstadoEntity{

    private final EstadoRepository repository;

    EstadoEntity(EstadoRepository repository) {
        this.repository = repository;
    }

    public Estado fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException("Estado nao encontrado"));
    }
}
