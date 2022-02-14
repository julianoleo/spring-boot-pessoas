package com.juliano.apipessoas.utils;

import com.juliano.apipessoas.exceptions.NoContentRuntimeException;
import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.model.Fone;
import com.juliano.apipessoas.repository.FoneRepository;
import com.juliano.apipessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaFone {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FoneRepository foneRepository;

    public void checaFone(String idCliente, Fone fone) {
        if(!pessoaExiste(idCliente)){
            throw new NoContentRuntimeException("Pessoa não existe.");
        }
        else if (fone.getFone() == null || fone.getFone().isEmpty() || fone.getFone().isBlank()) {
            throw new NotFoundException("Campo fone vazio ou inexistente.");
        }
        else if(foneExiste(fone.getFone())) {
            throw new NotFoundException("Fone já cadastrado.");
        }
        else { }
    }

    public void checaFoneUpdate(String idFone) {
        if(!foneExisteById(idFone)) {
            throw new NotFoundException("Fone inexistente.");
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

    public Boolean foneExisteById(String idFone) {
        var _fone = foneRepository.findById(idFone);
        if(_fone.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean foneExiste(String fone) {
        var _fone = foneRepository.findByFone(ValidaDocumento.removeCaracteresEspeciaisFone(fone));
        if(_fone.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
