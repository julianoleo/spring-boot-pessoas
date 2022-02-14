package com.juliano.apipessoas.controller;

import com.juliano.apipessoas.integracao.apiChaves.ChavesServiceClient;
import com.juliano.apipessoas.logs.APILogger;
import com.juliano.apipessoas.logs.models.ResponseDto;
import com.juliano.apipessoas.model.Endereco;
import com.juliano.apipessoas.service.EnderecoService;
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
@RequestMapping({"api/v1/endereco"})
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ChavesServiceClient validation;

    @PostMapping("/add")
    public ResponseEntity<Endereco> cadastraEndereco(
            HttpServletRequest request,
            @RequestBody Endereco endereco,
            @RequestHeader HttpHeaders headers
    ) {
        if(endereco.toString().isEmpty()) {
            throw new ConstraintViolationException("Endereço Vazia.", new HashSet<>());
        } else {
            validation.buscaValidation(request, headers);
            var _result = enderecoService.insert(endereco);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
        }
    }
}
