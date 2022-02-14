package com.juliano.apipessoas.repository;

import com.juliano.apipessoas.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends MongoRepository<Email, String> {
    Optional<Email> findByEmail(String email);

    Optional<List<Email>> findByIdCliente(String id);
}
