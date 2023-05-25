package com.algaworks.algafood.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import java.time.OffsetDateTime;




@Getter
@Setter
public class PedidoFilter {
    private Long clientId;
    private Long restauranteId;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
