package com.juliano.pessoas.integracao.buscaCep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceCep {

    @Autowired
    private ServiceCep service;

    public DadosCep buscaCep(String cep){
        var _result = service.buscaCep(cep);
        DadosCep dadosCep = new DadosCep();
            dadosCep.setStatus(_result.getBody().getStatus());
            dadosCep.setAddress(_result.getBody().getAddress());
            dadosCep.setCity(_result.getBody().getCity());
            dadosCep.setOk(_result.getBody().getOk());
            dadosCep.setCode(_result.getBody().getCode());
            dadosCep.setDistrict(_result.getBody().getDistrict());
            dadosCep.setState(_result.getBody().getState());
            dadosCep.setStatusText(_result.getBody().getStatusText());
            dadosCep.setMessage(_result.getBody().getMessage());
            return dadosCep;
    }
}