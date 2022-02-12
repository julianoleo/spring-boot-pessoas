package com.juliano.pessoas.service;

import com.juliano.pessoas.exceptions.NotFoundException;
import com.juliano.pessoas.integracao.buscaCep.ClientServiceCep;
import com.juliano.pessoas.model.Endereco;
import com.juliano.pessoas.repository.EnderecoRepository;
import com.juliano.pessoas.utils.ValidaCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ClientServiceCep apiCep;

    public Endereco insert(Endereco endereco) {
        var _pessoa = pessoaService.findById(endereco.getIdPessoa());
        if(_pessoa.isEmpty()){
            throw new NotFoundException("Pessoa não cadastrada.");
        }
        else {
            if(endereco.getCep() == null || endereco.getCep().isEmpty() || endereco.getCep().isBlank()){
                throw new NotFoundException("CEP em branco ou não informado.");
            }
            else if(!ValidaCep.verificaCep(endereco.getCep())) {
                throw new NotFoundException("CEP inválido.");
            }
            else {
                var _endCep = apiCep.buscaCep(endereco.getCep());
                if(_endCep.getStatus() != 200){
                    throw new NotFoundException("CEP não encontrado.");
                } else {
                    endereco.setCep(_endCep.getCode());
                    endereco.setUf(_endCep.getState());
                    endereco.setCidade(_endCep.getCity());
                    endereco.setBairro(_endCep.getDistrict());
                    endereco.setEndereco(_endCep.getAddress());
                    return enderecoRepository.insert(endereco);
                }
            }
        }
    }
}
