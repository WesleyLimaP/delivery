package com.delivery.project.app.api.model.dto.pedidoDto.response;

import com.delivery.project.app.api.model.dto.restauranteDto.response.RestauranteMinDto;
import com.delivery.project.app.api.model.dto.usuarioDto.response.UsuarioMinResponse;
import com.delivery.project.app.domain.model.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PedidoDto extends RepresentationModel<PedidoDto> {
    private Long id;
    private Double subTotal;
    private Double taxaFrete;
    private Double valorTotal;
    private LocalDate dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private UsuarioMinResponse cliente;
    private RestauranteMinDto restaurante;


}
