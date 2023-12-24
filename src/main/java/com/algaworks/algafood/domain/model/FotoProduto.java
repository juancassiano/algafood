package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    public Long getRestauranteId(){
        if(getProduto() != null){
            return getProduto().getRestaurante().getId();
        }
        return null;
    }
}
