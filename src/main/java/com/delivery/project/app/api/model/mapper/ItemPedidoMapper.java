package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.itemPedidoDto.request.ItemPedidoRequestDto;
import com.delivery.project.app.api.model.dto.itemPedidoDto.response.ItemPedidoResponseDto;
import com.delivery.project.app.api.model.mapper.utils.ProdutoMap;
import com.delivery.project.app.domain.exceptions.ProdutoNaoEncontradoException;
import com.delivery.project.app.domain.model.ItemPedido;
import com.delivery.project.app.domain.model.Produto;
import com.delivery.project.app.domain.repository.ProdutoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProdutoMap.class}
)
public interface ItemPedidoMapper {
    
    ItemPedidoResponseDto toModel(ItemPedido itemPedido);
    List<ItemPedidoResponseDto> toCollectionModel(List<ItemPedido> itemPedidos);

    @Mapping(target = "produto", source = "produto.id")
    ItemPedido toEntity(ItemPedidoRequestDto itemPedidoDto);
    void update(@MappingTarget ItemPedido itemPedido, ItemPedidoRequestDto itemPedidoDto);
}


