package com.bosonit.block13mongodb.repository;

import com.bosonit.block13mongodb.entity.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends MongoRepository<Persona, Long> {
}
