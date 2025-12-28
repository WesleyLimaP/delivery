package com.delivery.project.app.api.model.dto.endereco.response;

import com.delivery.project.app.domain.model.Endereco;
import com.delivery.project.app.api.model.dto.endereco.cidadeDto.response.CidadeDto;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
        @NotBlank
        @Max(9)
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
        private CidadeDto cidade;

    public EnderecoDto(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = new CidadeDto(endereco.getCidade());
    }
}
