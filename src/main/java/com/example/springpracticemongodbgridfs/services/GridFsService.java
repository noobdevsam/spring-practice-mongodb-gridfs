package com.example.springpracticemongodbgridfs.services;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface GridFsService {

    String store(MultipartFile file, String uploader) throws IOException;

    GridFsResource getFileAsResource(UUID id);

    void deleteById(UUID id);
}
