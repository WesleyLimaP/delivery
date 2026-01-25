package com.delivery.project.app.api.model.dto.formaDePagamentoDto.response;

import com.delivery.project.app.domain.model.FormaDePagamento;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
public class FormaDePagamentoResponseDto extends RepresentationModel<FormaDePagamentoResponseDto> {
    private Long id;
    private String descricao;

}
