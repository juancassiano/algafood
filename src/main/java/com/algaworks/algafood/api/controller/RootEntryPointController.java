package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootEntryPointController {
    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RootEntryPointModel root(){
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToCozinhas("cozinhas"));
        rootEntryPointModel.add(algaLinks.linkToPedidos("pedidos"));
        rootEntryPointModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        rootEntryPointModel.add(algaLinks.linkToUsuarios("usuários"));
        rootEntryPointModel.add(algaLinks.linkToGrupos("grupos"));
        rootEntryPointModel.add(algaLinks.linkToPermissoes("permissões"));
        rootEntryPointModel.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointModel.add(algaLinks.linkToEstados("estados"));
        rootEntryPointModel.add(algaLinks.linkToEstatisticas("estatisticas"));
        rootEntryPointModel.add(algaLinks.linkToCidades("cidades"));


        return rootEntryPointModel;
    }
    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{

    }
}
