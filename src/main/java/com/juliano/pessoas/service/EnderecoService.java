package com.juliano.pessoas.service;

import com.juliano.pessoas.model.Endereco;
import com.juliano.pessoas.repository.EnderecoRepository;
import com.juliano.pessoas.utils.ValidaEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ValidaEndereco validaEndereco;

    public Optional<Endereco> findByIdPessoaAndCep(String idPessoa, String cep) {
        return enderecoRepository.findByIdPessoaAndCep(idPessoa, cep);
    }

    public Endereco insert(Endereco endereco) {
        validaEndereco.checaEndereco(pessoaService.findById(endereco.getIdPessoa()), endereco);
        if(!findByIdPessoaAndCep(endereco.getIdPessoa(), endereco.getCep()).isEmpty()) {
            throw new RuntimeException("Endereço já cadastrado.");
        } else {
            return enderecoRepository.insert(validaEndereco.enriqueceEndCep(endereco));
        }
    }
}
