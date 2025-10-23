package com.example.springpracticemongodbgridfs.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DocumentMetadata extends MongoRepository<DocumentMetadata, UUID> {
}
