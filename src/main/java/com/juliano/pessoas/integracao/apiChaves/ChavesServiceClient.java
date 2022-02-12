package com.juliano.pessoas.integracao.apiChaves;

import com.juliano.pessoas.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ChavesServiceClient {

    @Autowired
    private ChavesService service;

    public void buscaValidation(HttpServletRequest request, HttpHeaders headers){
        var _result = service.buscaValidation(request, headers);
        ChavesModel chavesModel = new ChavesModel();
            chavesModel.setStatusCode(_result.getBody().getStatusCode());
            chavesModel.setDescStatus(_result.getBody().getDescStatus());

            if(!chavesModel.getStatusCode().equals(200)){
                throw new UnauthorizedException(chavesModel.getDescStatus());
            }
    }
}