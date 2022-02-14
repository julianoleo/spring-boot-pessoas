package com.juliano.apipessoas.utils.service;

import com.juliano.apipessoas.model.Fone;
import com.juliano.apipessoas.repository.FoneRepository;
import com.juliano.apipessoas.utils.ValidaDocumento;
import com.juliano.apipessoas.utils.ValidaFone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Fone> _fonePesquisado = foneRepository.findById(idFone);

        if(fone.getFone() != null) { _fonePesquisado.orElseThrow().setFone(ValidaDocumento.removeCaracteresEspeciaisFone(fone.getFone()));}
        if(fone.getDescFone() != null) { _fonePesquisado.orElseThrow().setDescFone(fone.getDescFone()); };

        return foneRepository.save(_fonePesquisado.orElseThrow());
    }
}
