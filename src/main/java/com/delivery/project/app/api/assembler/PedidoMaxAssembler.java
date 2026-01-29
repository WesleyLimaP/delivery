package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.*;
import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.filter.PedidoFilter;
import com.delivery.project.app.api.model.mapper.PedidoMaxMapper;
import com.delivery.project.app.domain.model.Pedido;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public  class PedidoMaxAssembler implements RepresentationModelAssembler<Pedido, PedidoMaxDto>{

    @Autowired
    private PedidoMaxMapper pedidoMaxMapper;

    @Override
    public PedidoMaxDto toModel(Pedido entity) {
        var pedidoDto = pedidoMaxMapper.toModel(entity);
        addLinks(pedidoDto);
        return pedidoDto;
    }

    public Pedido toEntity(PedidoRequestDto dto){
        return pedidoMaxMapper.toEntity(dto);
    }


    @Override
    public CollectionModel<PedidoMaxDto> toCollectionModel(Iterable<? extends Pedido> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }

    private void addLinks(@MappingTarget PedidoMaxDto pedidoDto) {
        var variables = new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        pedidoDto.add(Link.of(UriTemplate.of(WebMvcLinkBuilder
                .linkTo(methodOn(PedidoController.class)
                        .findAll(new PedidoFilter(), Pageable.ofSize(5)))
                .toString(), variables), "pedidos"));
        pedidoDto.getCliente().add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
                        .findById(pedidoDto.getCliente().getId()))
                .withRel("cliente"));
        pedidoDto.getRestaurante().add(WebMvcLinkBuilder.linkTo(methodOn(RestauranteController.class)
                        .findById(pedidoDto.getRestaurante().getId()))
                .withRel("restaurante"));
        pedidoDto.getFormaDePagamento().add(WebMvcLinkBuilder.linkTo(methodOn(FormaDePagamentoController.class)
                        .findById(pedidoDto.getFormaDePagamento().getId()))
                .withRel("formaDePagamento"));
        pedidoDto.getEndereco().getCidade().add(WebMvcLinkBuilder.linkTo(methodOn(CidadeController.class)
                        .findById(pedidoDto.getEndereco().getCidade().getId()))
                .withRel("cidade"));
        pedidoDto.getEndereco().getCidade().getEstado().add(WebMvcLinkBuilder.linkTo(methodOn(EstadoController.class)
                        .findById(pedidoDto.getEndereco().getCidade().getEstado().getId()))
                .withRel("estado"));
        pedidoDto.getItens().forEach(x -> x.getProduto()
                .add(WebMvcLinkBuilder.linkTo(methodOn(RestauranteProdutoController.class)
                                .findProdutoById(pedidoDto.getRestaurante().getId(), x.getProduto().getId()))
                        .withRel("produto")));

    }

}
