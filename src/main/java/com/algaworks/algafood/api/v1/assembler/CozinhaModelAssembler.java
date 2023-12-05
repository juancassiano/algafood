package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private AlgaLinks algaLinks;
    @Autowired
    private AlgaSecurity algaSecurity;

    public CozinhaModelAssembler(){
        super(CozinhaController.class,CozinhaModel.class);
    }

    @Override
    public CozinhaModel toModel(Cozinha cozinha){
        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(),cozinha);
        modelmapper.map(cozinha, CozinhaModel.class);

        if(algaSecurity.podeConsultarCozinhas()){
            cozinhaModel.add(linkTo(CozinhaController.class).withRel("cozinhas"));
        }

        return cozinhaModel;
    }


}
