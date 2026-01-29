package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.pedidoDto.request.PedidoRequestDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoDto;
import com.delivery.project.app.api.model.dto.pedidoDto.response.PedidoMaxDto;
import com.delivery.project.app.api.model.mapper.utils.*;
import com.delivery.project.app.domain.model.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {RestauranteMap.class,
                CidadeMap.class,
                FormaDePagamentoMap.class,
                ClienteMap.class,
                ItemPedidoMapper.class
        }
)
public interface PedidoMaxMapper {
    PedidoMaxDto toModel(Pedido pedido);
    List<PedidoDto> toCollectionModel(List<Pedido> pedidos);
    
    @Mapping(target = "restaurante", source = "restaurante.id")
    @Mapping(target = "endereco.cidade", source = "endereco.cidade.id")
    @Mapping(target = "formaDePagamento", source = "formaDePagamento.id")
    @Mapping(target = "cliente", source = "cliente.id")
    Pedido toEntity(PedidoRequestDto pedidoDto);
    
    void update(@MappingTarget Pedido pedido, PedidoMaxDto pedidoDto);
    
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
