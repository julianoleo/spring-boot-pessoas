package com.juliano.apipessoas.repository;

import com.juliano.apipessoas.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    Pessoa findByDocumento(String doc);
}
