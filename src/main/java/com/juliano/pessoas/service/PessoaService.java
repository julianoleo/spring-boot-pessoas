package com.juliano.pessoas.service;

import com.juliano.pessoas.exceptions.NoContentRuntimeException;
import com.juliano.pessoas.exceptions.NotFoundException;
import com.juliano.pessoas.model.Pessoa;
import com.juliano.pessoas.repository.PessoaRepository;
import com.juliano.pessoas.utils.ValidaDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Optional<Pessoa> findByDoc(String doc) {
        return Optional.ofNullable(pessoaRepository.findByDocumento(doc));
    }

    public Optional<Pessoa> findById(String id) {
        var _pessoa =  pessoaRepository.findById(id);
        if(_pessoa.isEmpty()){
            throw new NoContentRuntimeException();
        } else {
            return _pessoa;
        }
    }

    public Pessoa insert(Pessoa pessoa) {
        var _pessoa = findByDoc(pessoa.getDocumento());
        if(_pessoa.isEmpty()) {
            if(pessoa.getDocumento().isEmpty() || pessoa.getDocumento().isBlank()) {
                throw new NotFoundException("Campo documento vazio ou inexistente.");
            }
            else {
                if(pessoa.getNome() == null || pessoa.getNome().isEmpty() || pessoa.getNome().isBlank()) {
                    throw new NotFoundException("Campo nome vazio ou inexistente.");
                }
                else {
                    if(ValidaDocumento.isCPF(pessoa.getDocumento())) {
                        pessoa.setTipoDoc("CPF");
                        return pessoaRepository.insert(pessoa);
                    }
                    else if(ValidaDocumento.isCNPJ(pessoa.getDocumento())){
                        pessoa.setTipoDoc("CNPJ");
                        return pessoaRepository.insert(pessoa);
                    }
                    else {
                        throw new NotFoundException("Documento inválido.");
                    }
                }
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
