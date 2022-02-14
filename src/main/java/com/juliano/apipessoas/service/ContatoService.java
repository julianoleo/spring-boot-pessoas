package com.juliano.apipessoas.service;

import com.juliano.apipessoas.model.Contato;
import com.juliano.apipessoas.repository.ContatoRepository;
import com.juliano.apipessoas.utils.ValidaContato;
import com.juliano.apipessoas.utils.ValidaDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ValidaContato validaContato;

    public Optional<List<Contato>> findByIdCliente(String idCliente) {
        return contatoRepository.findByIdCliente(idCliente);
    }

    public Optional<Contato> findByIdClienteAndFone(String idCliente, String fone) {
        return contatoRepository.findByIdClienteAndFone(idCliente, fone);
    }

    public Optional<Contato> findByIdClienteAndEmail(String idClente, String email) {
        return contatoRepository.findByIdClienteAndEmail(idClente, email);
    }

    public Contato insert(Contato contato) {
        validaContato.checaContato(contato);
        if(existeContato(contato)) { throw new RuntimeException("Contado já cadastrado."); }
        return contatoRepository.insert(contato);
    }

    private Boolean existeContato(Contato contato) {
        List<Boolean> _verifica = new ArrayList<Boolean>();
        _verifica.add(verificaFone(contato.getIdCliente(), contato.getFone()));
        _verifica.add(verificaEmail(contato.getIdCliente(), contato.getEmail()));

        if(_verifica.contains(true)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verificaFone(String idCliente, String fone) {
        var _contato = findByIdClienteAndFone(idCliente, ValidaDocumento.removeCaracteresEspeciaisFone(fone));
        if(!_contato.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    private Boolean verificaEmail(String idCliente, String email) {
        var _contato = findByIdClienteAndEmail(idCliente, email);
        if(!_contato.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
}
