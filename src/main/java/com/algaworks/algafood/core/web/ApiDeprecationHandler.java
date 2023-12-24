package com.algaworks.algafood.core.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ApiDeprecationHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().startsWith("/v1/")){
            /*
            Remove a versão da API
             response.setStatus(HttpStatus.GONE.value());
            return false;
             */


            response.addHeader("X-Algafood-Deprecated",
                    "Essa versão da API está depreciada e deixará de existir a partir de XX/XX/XXXX."
                            + "Use a versão mais atual da API.");
        }
        return true;
    }
}
