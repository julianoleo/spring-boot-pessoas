package com.juliano.pessoas.controller;

import com.juliano.pessoas.logs.APILogger;
import com.juliano.pessoas.logs.models.ResponseDto;
import com.juliano.pessoas.model.Pessoa;
import com.juliano.pessoas.service.PessoaService;
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

    @GetMapping("/{doc}")
    public ResponseEntity<Pessoa> pessoaByDocumento(
            HttpServletRequest request,
            @PathVariable(name = "doc") String doc,
            @RequestHeader HttpHeaders headers
    ) {
        var _result = pessoaService.buscaPorDoc(doc);
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
