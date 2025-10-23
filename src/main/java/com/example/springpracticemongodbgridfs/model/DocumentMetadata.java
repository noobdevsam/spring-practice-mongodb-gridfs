package com.example.springpracticemongodbgridfs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "doc_metadata")
public class DocumentMetadata {

    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String owner;

    public DocumentMetadata(String title, String owner) {
        this.title = title;
        this.owner = owner;
    }

    public DocumentMetadata(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
