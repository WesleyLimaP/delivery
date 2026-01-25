package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.controller.PedidoController;
import com.delivery.project.app.api.controller.RestauranteController;
import com.delivery.project.app.api.controller.UsuarioController;
import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.domain.exceptions.CidadeNaoEncontradaException;
import com.delivery.project.app.domain.exceptions.FormaDePagamentoEncontradaException;
import com.delivery.project.app.domain.exceptions.ProdutoNaoEncontradoException;
import com.delivery.project.app.domain.exceptions.RestauranteNaoEncontradoException;
import com.delivery.project.app.domain.model.*;
import com.delivery.project.app.domain.repository.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring",
        uses = {RestauranteMap.class,
                CidadeMap.class,
                FormaDePagamentoMap.class,
                ClienteMap.class,
                ItemPedidoAssembler.class
        }
)

public interface PedidoAssembler extends RepresentationModelAssembler<Pedido, PedidoDto>{

    PedidoDto toModel(Pedido pedido);
    List<PedidoDto> toCollectionModel(List<Pedido> pedidos);
    @Mapping(target = "restaurante", source = "restaurante.id")
    @Mapping(target = "endereco.cidade", source = "endereco.cidade.id")
    @Mapping(target = "formaDePagamento", source = "formaDePagamento.id")
    @Mapping(target = "cliente", source = "cliente.id")
    Pedido toEntity( PedidoRequestDto pedidoDto);
    void update(@MappingTarget Pedido pedido, PedidoDto pedidoDto);
    @AfterMapping
    default void addLinks(@MappingTarget PedidoDto pedidoDto ) {
        pedidoDto.add(WebMvcLinkBuilder.linkTo(methodOn(PedidoController.class)
                .findById(pedidoDto.getId()))
                .withSelfRel());
        pedidoDto.getCliente().add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioController.class)
                .findById(pedidoDto.getCliente().getId()))
                .withRel("cliente"));
        pedidoDto.getRestaurante().add(WebMvcLinkBuilder.linkTo(methodOn(RestauranteController.class)
                .findById(pedidoDto.getRestaurante().getId()))
                .withRel("restaurante"));
    }
    @AfterMapping
    default void mapItens(@MappingTarget Pedido pedido, PedidoRequestDto pedidoDto) {
        pedido.getItens().forEach(x-> x.setPedido(pedido));
            }
    @AfterMapping
    default void calcItens(@MappingTarget Pedido pedido ) {
        pedido.getTaxaFrete();
        pedido.getSubTotal();
        pedido.getValorTotal();
    }
}
@Component
class RestauranteMap {
    @Autowired
    private RestauranteRepository repository;

    public Restaurante fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("Produto not found") {
        });
    }
}@Component
class ClienteMap {
    @Autowired
    private UsuarioRepository repository;

    public Usuario fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException("Produto not found") {
        });
    }
}

@Component
class CidadeMap {
    private final CidadeRepository repository;

    CidadeMap(CidadeRepository repository) {
        this.repository = repository;
    }

    public Cidade fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException("cidade not found") {
        });
    }
}
@Component
class FormaDePagamentoMap{

    private final FormaDePagamentoRepository repository;

    FormaDePagamentoMap(FormaDePagamentoRepository repository) {
        this.repository = repository;
    }

    public FormaDePagamento fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new FormaDePagamentoEncontradaException("Produto not found") {
        });
    }
    public List<FormaDePagamento> fromIds(Set<Long> ids) {
        return repository.findAllById(ids);
    }
}
@Component
class ProdutoMap{
    @Autowired
    private ProdutoRepository repository;

    public Produto fromId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("Produto not found") {
        });
    }
}