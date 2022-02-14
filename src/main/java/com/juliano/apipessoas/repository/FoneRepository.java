package com.juliano.apipessoas.repository;

import com.juliano.apipessoas.model.Fone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoneRepository extends MongoRepository<Fone, String> {
    Optional<Fone> findByFone(String removeCaracteresEspeciaisFone);

    Optional<List<Fone>> findByIdCliente(String id);

}
