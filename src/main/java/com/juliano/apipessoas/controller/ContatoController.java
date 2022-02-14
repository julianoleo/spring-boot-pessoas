package com.juliano.apipessoas.controller;

import com.juliano.apipessoas.integracao.apiChaves.ChavesServiceClient;
import com.juliano.apipessoas.logs.APILogger;
import com.juliano.apipessoas.logs.models.ResponseDto;
import com.juliano.apipessoas.model.Email;
import com.juliano.apipessoas.model.Fone;
import com.juliano.apipessoas.service.EmailService;
import com.juliano.apipessoas.service.FoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@RestController
@Validated
@RequestMapping({"api/v1/contato"})
public class ContatoController {

    @Autowired
    private FoneService foneService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ChavesServiceClient validation;

    @PostMapping("/add/fone/{idCliente}")
    public ResponseEntity<?> cadastraFone(
            HttpServletRequest request,
            @PathVariable(name = "idCliente") String idCliente,
            @RequestBody Fone fone,
            @RequestHeader HttpHeaders headers
    ) {
        if(fone.toString().isEmpty()) {
            throw new ConstraintViolationException("Fone Vazio.", new HashSet<>());
        } else {
            validation.buscaValidation(request, headers);
            var _result = foneService.insert(idCliente, fone);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
        }
    }

    @PostMapping("/add/email/{idCliente}")
    public ResponseEntity<?> cadastraEmail(
            HttpServletRequest request,
            @PathVariable(name = "idCliente") String idCliente,
            @RequestBody Email email,
            @RequestHeader HttpHeaders headers
    ) {
        if(email.toString().isEmpty()) {
            throw new ConstraintViolationException("Email Vazio.", new HashSet<>());
        } else {
            validation.buscaValidation(request, headers);
            var _result = emailService.insert(idCliente, email);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
        }
    }
}
