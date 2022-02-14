package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.exceptions.NoContentRuntimeException;
import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.model.Email;
import com.juliano.apipessoas.repository.EmailRepository;
import com.juliano.apipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaEmail {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailRepository emailRepository;

    public void checaEmail(String idCliente, Email email) {
        if(!pessoaExiste(idCliente)){
            throw new NoContentRuntimeException("Pessoa não existe.");
        }
        else if(email.getEmail() == null || email.getEmail().isEmpty() || email.getEmail().isBlank()) {
            throw new NotFoundException("Campo email vazio ou inexistente.");
        }
        else if(emailExiste(email.getEmail())) {
            throw new NotFoundException("Email já cadastrado.");
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

    private Boolean emailExiste(String email) {
        var _fone = emailRepository.findByEmail(email);
        if(_fone.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
