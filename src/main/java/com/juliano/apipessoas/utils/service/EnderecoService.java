package com.juliano.apipessoas.utils.service;

import com.juliano.apipessoas.model.Endereco;
import com.juliano.apipessoas.repository.EnderecoRepository;
import com.juliano.apipessoas.utils.ValidaEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
