package com.example.springpracticemongodbgridfs.repos;

import com.example.springpracticemongodbgridfs.model.DocumentMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DocumentMetadataRepository extends MongoRepository<DocumentMetadata, UUID> {
}
