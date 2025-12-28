package com.delivery.project.app.domain.service.util.mapper.endereco;

import com.delivery.project.app.api.model.dto.endereco.request.EnderecoRequestDto;
import com.delivery.project.app.domain.model.Endereco;
import com.delivery.project.app.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    @Autowired
    CidadeService cidadeService;
    public Endereco toEntity(EnderecoRequestDto dto){
        Endereco endereco = new Endereco();
        endereco.setBairro(dto.getBairro());
        endereco.setCep(dto.getCep());
        endereco.setNumero(dto.getNumero());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCidade(cidadeService.findByIdOrElseThrow(dto.getCidade().id()));

        return endereco;
    }
}
