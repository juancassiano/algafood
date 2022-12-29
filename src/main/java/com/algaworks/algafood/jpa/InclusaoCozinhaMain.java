package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = appContext.getBean(CozinhaRepository.class);

       Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("africana");

        cozinha1 = cozinhaRepository.salvar(cozinha1);
        cozinha2 = cozinhaRepository.salvar(cozinha2);
    }
}
