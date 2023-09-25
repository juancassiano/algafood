package com.algaworks.algafood.core.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Setter
@Getter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {
    @NotNull
    private String remetente;
}
