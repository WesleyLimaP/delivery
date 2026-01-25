package com.delivery.project.app.api.assembler;

import com.delivery.project.app.api.model.dto.itemPedidoDto.request.ItemPedidoRequestDto;
import com.delivery.project.app.api.model.dto.itemPedidoDto.response.ItemPedidoResponseDto;
import com.delivery.project.app.domain.model.ItemPedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProdutoMap.class
        }
)
interface ItemPedidoAssembler {
    ItemPedidoResponseDto toModel(ItemPedido itemPedido);
    List<ItemPedidoResponseDto> toCollectionModel(List<ItemPedido> itemPedidos);

    @Mapping(target = "produto", source = "produto.id")
    ItemPedido toEntity(ItemPedidoRequestDto itemPedidoDto);
    void update(@MappingTarget ItemPedido itemPedido, ItemPedidoRequestDto itemPedidoDto);

}
