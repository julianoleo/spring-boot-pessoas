package com.juliano.apipessoas.service;

import com.juliano.apipessoas.exceptions.NoContentRuntimeException;
import com.juliano.apipessoas.exceptions.NotFoundException;
import com.juliano.apipessoas.model.dto.FinalPessoaDto;
import com.juliano.apipessoas.model.Pessoa;
import com.juliano.apipessoas.repository.EnderecoRepository;
import com.juliano.apipessoas.repository.FoneRepository;
import com.juliano.apipessoas.repository.PessoaRepository;
import com.juliano.apipessoas.utils.ToDto;
import com.juliano.apipessoas.utils.ValidaDocumento;
import com.juliano.apipessoas.utils.ValidaPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private FoneRepository foneRepository;

    @Autowired
    private ValidaPessoa validaPessoa;

    @Autowired
    private ToDto toDto;

    public Optional<Pessoa> findByDoc(String doc) {
        return Optional.ofNullable(pessoaRepository.findByDocumento(doc));
    }

    public Optional<Pessoa> findById(String id) {
        return pessoaRepository.findById(id);
    }

    public Optional<FinalPessoaDto> buscaById(String id) {
        var _pessoa = pessoaRepository.findById(id);
        if(_pessoa.isEmpty()){
            throw new NoContentRuntimeException();
        } else {
            var _endereco = enderecoRepository.findByIdPessoa(_pessoa.orElseThrow().getId());
            var _fone = foneRepository.findByIdCliente(_pessoa.orElseThrow().getId());
            var _result =  toDto.pessoaDto(_pessoa.orElseThrow(), _endereco.orElseThrow(), _fone.orElseThrow());
            return Optional.ofNullable(_result);
        }
    }

    public Pessoa insert(Pessoa pessoa) {
        validaPessoa.checaPessoa(pessoa);
        var _pessoa = findByDoc(pessoa.getDocumento());
        if(_pessoa.isEmpty()) {
            if(ValidaDocumento.isCPF(pessoa.getDocumento())) {
                pessoa.setTipoDoc("CPF");
                return pessoaRepository.insert(pessoa);
            }
            else if(ValidaDocumento.isCNPJ(pessoa.getDocumento())) {
                pessoa.setTipoDoc("CNPJ");
                return pessoaRepository.insert(pessoa);
            }
            else {
                throw new RuntimeException("Documento inválido.");
            }
        }
        else {
            throw new NotFoundException("Pessoa já cadastada.");
        }
    }

    public Pessoa buscaPorDoc(String doc){
        var _pessoa = findByDoc(doc);
        if(_pessoa.isEmpty()){
            throw new NoContentRuntimeException();
        }
        else {
            return _pessoa.orElseThrow();
        }
    }
}
