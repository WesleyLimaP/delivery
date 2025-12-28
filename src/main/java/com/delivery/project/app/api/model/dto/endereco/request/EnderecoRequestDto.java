package com.delivery.project.app.api.model.dto.endereco.request;

import com.delivery.project.app.api.model.dto.endereco.cidadeDto.request.CidadeIdDto;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDto {
    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @ManyToOne
    @NotNull
    private CidadeIdDto cidade;


}
