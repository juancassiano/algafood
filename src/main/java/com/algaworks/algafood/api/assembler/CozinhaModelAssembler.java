package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.CozinhaController;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

    public CozinhaModelAssembler(){
        super(CozinhaController.class,CozinhaModel.class);
    }

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public CozinhaModel toModel(Cozinha cozinha){
        CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(),cozinha);
        modelmapper.map(cozinha, CozinhaModel.class);
        cozinhaModel.add(linkTo(CozinhaController.class).withRel("cozinhas"));

        return cozinhaModel;
    }


}
