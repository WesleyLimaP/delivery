package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.*;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.domain.model.Pedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface PedidoMaxAssembler extends RepresentationModelAssembler<Pedido, PedidoMaxDto>{

    @Override
    PedidoMaxDto toModel(Pedido pedido);
    List<PedidoDto> toCollectionModel(List<Pedido> pedidos);
    Pedido toEntity(PedidoMaxDto pedidoDto);
    void update(@MappingTarget Pedido pedido, PedidoMaxDto pedidoDto);
    @AfterMapping
    default void addLinks(@MappingTarget PedidoMaxDto pedidoDto ) {
        pedidoDto.add(WebMvcLinkBuilder.linkTo(methodOn(PedidoController.class)
                .findById(pedidoDto.getId()))
                .withSelfRel());
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
