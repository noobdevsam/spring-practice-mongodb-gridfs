package com.example.springpracticemongodbgridfs.services.impl;

import com.example.springpracticemongodbgridfs.services.GridFsService;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class GridFsServiceImpl implements GridFsService {
    @Override
    public String store(MultipartFile file, String uploader) throws IOException {
        return "";
    }

    @Override
    public GridFsResource getFileAsResource(UUID id) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
