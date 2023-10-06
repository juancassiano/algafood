package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.openapiModel.PageableModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    TypeResolver typeResolver = new TypeResolver();

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }


    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
//                    .paths(PathSelectors.ant("/restaurantes/*"))

                    .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .apiInfo(apiInfo())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Grupos", "Gerencia os grupos"));
    }


    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("API aberta para clientes e restaurantes")
                .version("1.0")
                .contact(new Contact("Juan Cassiano", "https://github.com/juancassiano", "juancassiano@hotmail.com"))
                .build();
    }


    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description("Erro interno do Servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(problemBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(comoString(HttpStatus.BAD_REQUEST))
                        .description("Requisição inválida (erro do cliente)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(problemBuilder())
                        .build()
        );
    }


    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description("Erro interno do Servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(problemBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(comoString(HttpStatus.NOT_ACCEPTABLE))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(comoString(HttpStatus.BAD_REQUEST))
                        .description("Requisição inválida (erro do cliente)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(problemBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(comoString(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(problemBuilder())
                        .build()
        );
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(comoString(HttpStatus.INTERNAL_SERVER_ERROR))
                        .description("Erro interno do Servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(problemBuilder())
                        .build(),
                new ResponseBuilder()
                        .code(comoString(HttpStatus.NOT_ACCEPTABLE))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build()
        );
    }

    private String comoString(HttpStatus httpStatus) {
        return String.valueOf(httpStatus.value());
    }

    private Consumer<RepresentationBuilder> problemBuilder() {
        return r -> r.model(m -> m.name("Problema")
                .referenceModel(
                        ref -> ref.key(
                                k -> k.qualifiedModelName(
                                        q -> q.name("Problema")
                                                .namespace("com.algaworks.algafood.api.exceptionhandler")
                                ))));
    }

}
