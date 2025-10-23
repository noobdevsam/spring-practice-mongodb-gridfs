package com.example.springpracticemongodbgridfs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "doc_metadata")
public class DocumentMetadata {

    @Id
    private String id;
    private String title;
    private String owner;

    public DocumentMetadata(String id, String title, String owner) {
        this.id = id;
        this.title = title;
        this.owner = owner;
    }

    public DocumentMetadata(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof DocumentMetadata that)) return false;

        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(owner);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentMetadata{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

}
