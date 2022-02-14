package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.exceptions.NoContentRuntimeException;
import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.model.Contato;
import com.juliano.apipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidaContato {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void checaContato(Contato contato) {
        if(contato.getIdCliente() == null || contato.getIdCliente().isEmpty() || contato.getIdCliente().isBlank()) {
            throw new NotFoundException("Campo idCliente vazio ou inexistente.");
        }
        if(!pessoaExiste(contato.getIdCliente())) {
            throw new NoContentRuntimeException("Pessoa não existe.");
        }
        else { }
    }

    private Boolean pessoaExiste(String idCliente) {
        var _pessoa = pessoaRepository.findById(idCliente);
        if(_pessoa.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
