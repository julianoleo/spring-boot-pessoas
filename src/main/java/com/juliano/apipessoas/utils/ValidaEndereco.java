package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.integracao.buscaCep.ClientServiceCep;
import com.juliano.apipessoas.model.Endereco;
import com.juliano.apipessoas.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidaEndereco {

    @Autowired
    private ClientServiceCep apiCep;

    public void checaEndereco(Optional<Pessoa> pessoa, Endereco endereco) {
        if(pessoa.isEmpty()) {
            throw new NotFoundException("Pessoa não cadastrada.");
        }
        else if(endereco.getCep() == null || endereco.getCep().isEmpty() || endereco.getCep().isBlank()) {
            throw new NotFoundException("CEP em branco ou não informado.");
        }
        else if(!ValidaCep.verificaCep(endereco.getCep())) {
            throw new NotFoundException("CEP inválido.");
        }
        else if(apiCep.buscaCep(endereco.getCep()).getStatus() != 200) {
            throw new NotFoundException("CEP inexistente.");
        }
        else { };
    }

    public Endereco enriqueceEndCep(Endereco endereco) {
        var _endCep = apiCep.buscaCep(endereco.getCep());
        endereco.setCep(_endCep.getCode());
        endereco.setUf(_endCep.getState());
        endereco.setCidade(_endCep.getCity());
        endereco.setBairro(_endCep.getDistrict());
        endereco.setEndereco(_endCep.getAddress());
        return endereco;
    }
}
