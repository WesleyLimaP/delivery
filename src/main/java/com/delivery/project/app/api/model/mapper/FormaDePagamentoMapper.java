package com.delivery.project.app.api.model.mapper;

import com.delivery.project.app.api.model.dto.formaDePagamentoDto.request.FormaDePagamentoDescricaoDto;
import com.delivery.project.app.api.model.dto.formaDePagamentoDto.response.FormaDePagamentoResponseDto;
import com.delivery.project.app.domain.model.FormaDePagamento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormaDePagamentoMapper {
    
    FormaDePagamentoResponseDto toModel(FormaDePagamento formaDePagamento);
    FormaDePagamento toEntity(FormaDePagamentoResponseDto formaDePagamento);
    FormaDePagamento toEntity(FormaDePagamentoDescricaoDto formaDePagamento);
    List<FormaDePagamentoResponseDto> toCollectionModel(List<FormaDePagamento> formaDePagamento);
    void update(@MappingTarget FormaDePagamento formaDePagamento, FormaDePagamentoDescricaoDto formaDePagamentoResponseDto);
}
