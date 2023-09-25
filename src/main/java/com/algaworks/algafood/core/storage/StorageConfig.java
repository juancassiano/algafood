package com.algaworks.algafood.core.storage;

import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.LocalFotoStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.algaworks.algafood.core.storage.StorageProperties.TipoStorage;

@Configuration
public class StorageConfig {

    @Bean
    public FotoStorageService fotoStorageService() {
         return new LocalFotoStorageService();
    }
}
