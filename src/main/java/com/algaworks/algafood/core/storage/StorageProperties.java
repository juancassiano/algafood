package com.algaworks.algafood.core.storage;
import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

    private Local local = new Local();
    private TipoStorage tipo = TipoStorage.LOCAL;

    public enum TipoStorage{
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {
        private Path diretorioFotos;
    }

}