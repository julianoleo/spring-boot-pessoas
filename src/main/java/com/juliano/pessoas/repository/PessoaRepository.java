package com.juliano.pessoas.repository;

import com.juliano.pessoas.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    Pessoa findByDocumento(String doc);
}
