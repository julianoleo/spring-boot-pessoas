package com.juliano.pessoas.integracao.apiChaves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChavesServiceClient {

    @Autowired
    private ChavesService service;

    public ChavesModel buscaCep(String cep){
        var _result = service.buscaCep(cep);
        ChavesModel chavesModel = new ChavesModel();
            chavesModel.setStatus(_result.getBody().getStatus());
            chavesModel.setAddress(_result.getBody().getAddress());
            chavesModel.setCity(_result.getBody().getCity());
            chavesModel.setOk(_result.getBody().getOk());
            chavesModel.setCode(_result.getBody().getCode());
            chavesModel.setDistrict(_result.getBody().getDistrict());
            chavesModel.setState(_result.getBody().getState());
            chavesModel.setStatusText(_result.getBody().getStatusText());
            chavesModel.setMessage(_result.getBody().getMessage());
            return chavesModel;
    }
}