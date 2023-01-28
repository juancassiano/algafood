package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @ManyToOne
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @JoinColumn(nullable = false)
    private Estado estado;
}

