package com.juliano.apipessoas.service;

import com.juliano.apipessoas.model.Fone;
import com.juliano.apipessoas.repository.FoneRepository;
import com.juliano.apipessoas.utils.ValidaFone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoneService {

    @Autowired
    private FoneRepository foneRepository;

    @Autowired
    private ValidaFone validaFone;

    public Fone insert(String idCliente, Fone fone) {
        validaFone.checaFone(idCliente, fone);
        fone.setIdCliente(idCliente);
        return foneRepository.insert(fone);
    }

    public Fone update(String idFone, Fone fone) {
        validaFone.checaFoneUpdate(idFone);
        var _fone = foneRepository.findById(idFone);
        _fone.orElseThrow().setFone(fone.getFone());
        _fone.orElseThrow().setDescFone(fone.getDescFone());
        return foneRepository.update(_fone);
    }
}
