package com.example.springpracticemongodbgridfs.repos;

import com.example.springpracticemongodbgridfs.model.DocumentMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentMetadataRepository extends MongoRepository<DocumentMetadata, String> {
}
