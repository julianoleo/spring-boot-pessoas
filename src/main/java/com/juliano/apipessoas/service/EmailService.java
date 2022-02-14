package com.juliano.apipessoas.service;

import com.juliano.apipessoas.model.Email;
import com.juliano.apipessoas.repository.EmailRepository;
import com.juliano.apipessoas.utils.ValidaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ValidaEmail validaEmail;

    public Email insert(String idCliente, Email email) {
        validaEmail.checaEmail(idCliente, email);
        email.setIdCliente(idCliente);
        return emailRepository.insert(email);
    }
}
