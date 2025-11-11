package com.delivery.project.app.api.model.dto.formaDePagamentoDto.response;

import com.delivery.project.app.domain.model.FormaDePagamento;
import lombok.Data;

@Data
public class FormaDePagamentoResponseDto {
    private Long id;
    private String descricao;

    public FormaDePagamentoResponseDto(FormaDePagamento x) {
        this.id = x.getId();
        this.descricao = x.getDescricao();
    }
}
