package com.delivery.project.app.dto.restauranteDto;

import com.delivery.project.app.domain.model.Cidade;
import com.delivery.project.app.domain.model.Endereco;
import com.delivery.project.app.dto.cidadeDto.CidadeDto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto {
        private String cep;
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        @ManyToOne
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
