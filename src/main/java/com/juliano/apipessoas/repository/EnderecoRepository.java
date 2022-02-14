package com.juliano.apipessoas.repository;

import com.juliano.apipessoas.model.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends MongoRepository<Endereco, String> {
    Optional<Endereco> findByIdPessoaAndCep(String idPessoa, String cep);
    Optional<List<Endereco>> findByIdPessoa(String id);
}
