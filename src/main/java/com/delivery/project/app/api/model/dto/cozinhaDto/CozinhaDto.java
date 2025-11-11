package com.delivery.project.app.api.model.dto.cozinhaDto;

import com.delivery.project.app.domain.model.Cozinha;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CozinhaDto{
    private Long id;
    @NotBlank
    private String nome;

    public CozinhaDto(Cozinha cozinha) {
        this.id = cozinha.getId();
        this.nome = cozinha.getNome();
    }

    public CozinhaDto(Long id) {
        this.id = id;
    }
}
