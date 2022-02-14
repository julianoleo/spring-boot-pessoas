package com.juliano.apipessoas.repository;

import com.juliano.apipessoas.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email, String> {
}
