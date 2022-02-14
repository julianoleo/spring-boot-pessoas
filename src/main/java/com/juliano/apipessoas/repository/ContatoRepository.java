package com.juliano.apipessoas.repository;

import com.juliano.apipessoas.model.Contato;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends MongoRepository<Contato, String> {
    Optional<Contato> findByIdClienteAndFone(String idCliente, String fone);

    Optional<Contato> findByIdClienteAndEmail(String idClente, String email);

    Optional<List<Contato>> findByIdCliente(String idCliente);
}
