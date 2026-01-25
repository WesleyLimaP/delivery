package com.delivery.project.app.api.model.dto.cozinhaDto;

import com.delivery.project.app.domain.model.Cozinha;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CozinhaDto extends RepresentationModel<CozinhaDto>{
    private Long id;
    @NotBlank
    private String nome;

    public CozinhaDto(Cozinha cozinha) {
        this.id = cozinha.getId();
        this.nome = cozinha.getNome();
    }

}
