package com.juliano.apipessoas.controller;

import com.juliano.apipessoas.integracao.apiChaves.ChavesServiceClient;
import com.juliano.apipessoas.logs.APILogger;
import com.juliano.apipessoas.logs.models.ResponseDto;
import com.juliano.apipessoas.model.Pessoa;
import com.juliano.apipessoas.utils.service.PessoaService;
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
@RequestMapping({"api/v1/pessoas"})
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ChavesServiceClient validation;

    @GetMapping("/{doc}")
    public ResponseEntity<Pessoa> pessoaByDocumento(
            HttpServletRequest request,
            @PathVariable(name = "doc") String doc,
            @RequestHeader HttpHeaders headers
    ) {
        validation.buscaValidation(request, headers);
        var _result = pessoaService.buscaPorDoc(doc);
        var _response = new ResponseEntity<>(_result, HttpStatus.OK);
        var _responseLog = new ResponseDto<>(_result);
        APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
        return _response;
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<?> pessoaById(
            HttpServletRequest request,
            @PathVariable(name = "id") String id,
            @RequestHeader HttpHeaders headers
    ) {
        validation.buscaValidation(request, headers);
        var _result = pessoaService.buscaById(id).orElseThrow();
        var _response = new ResponseEntity<>(_result, HttpStatus.OK);
        var _responseLog = new ResponseDto<>(_result);
        APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
        return _response;
    }

    @PostMapping("/add")
    public ResponseEntity<Pessoa> cadastraPessoa(
            HttpServletRequest request,
            @RequestBody Pessoa pessoa,
            @RequestHeader HttpHeaders headers
    ) {
        validation.buscaValidation(request, headers);
        if(pessoa.toString().isEmpty()) {
            throw new ConstraintViolationException("Pessoa Vazia.", new HashSet<>());
        } else {
            var _result = pessoaService.insert(pessoa);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
        }
    }
}
