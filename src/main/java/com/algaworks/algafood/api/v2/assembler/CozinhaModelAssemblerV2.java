package com.algaworks.algafood.api.v2.assembler;

import com.algaworks.algafood.api.v2.AlgaLinksV2;
import com.algaworks.algafood.api.v2.controller.CozinhaControllerV2;
import com.algaworks.algafood.api.v2.model.CozinhaModelV2;
import com.algaworks.algafood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CozinhaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {

    public CozinhaModelAssemblerV2(){
        super(CozinhaControllerV2.class,CozinhaModelV2.class);
    }

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private AlgaLinksV2 algaLinks;
    @Override
    public CozinhaModelV2 toModel(Cozinha cozinha){
        CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(),cozinha);
        modelmapper.map(cozinha, CozinhaModelV2.class);
        cozinhaModel.add(linkTo(CozinhaControllerV2.class).withRel("cozinhas"));

        return cozinhaModel;
    }


}
