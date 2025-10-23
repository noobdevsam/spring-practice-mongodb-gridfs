package com.example.springpracticemongodbgridfs.services;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GridFsService {

    String store(MultipartFile file, String uploader) throws IOException;

    GridFsResource getFileAsResource(String id);

    void deleteById(String id);
}
