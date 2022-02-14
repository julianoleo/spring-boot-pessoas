package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.exceptions.NoContentRuntimeException;
import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.model.Email;
import com.juliano.apipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaEmail {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void checaEmail(String idCliente, Email email) {
        if(!pessoaExiste(idCliente)){
            throw new NoContentRuntimeException("Pessoa não existe.");
        }
        else if(email.getEmail() == null || email.getEmail().isEmpty() || email.getEmail().isBlank()) {
            throw new NotFoundException("Campo email vazio ou inexistente.");
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
