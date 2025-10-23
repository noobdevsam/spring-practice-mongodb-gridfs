package com.example.springpracticemongodbgridfs.services.impl;

import com.example.springpracticemongodbgridfs.services.GridFsService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class GridFsServiceImpl implements GridFsService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;

    public GridFsServiceImpl(GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
    }

    @Override
    public String store(MultipartFile file, String uploader) throws IOException {
//
//        var metadata = new BasicQuery("{}");

        // GridFsTemplate.store supports InputStream, filename, contentType, metadata (as org.bson.Document)
        var meta = new Document();
        meta.put("uploader", uploader);
        meta.append("originalFilename", file.getOriginalFilename());
        meta.append("size", file.getSize());

        try (var inputStream = file.getInputStream()) {
            var id = gridFsTemplate.store(
                    inputStream,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    meta
            );
            return id.toHexString();
        }

    }

    @Override
    public GridFsResource getFileAsResource(UUID id) {

        var gridFsFile = gridFsTemplate.findOne(
                new Query(
                        Criteria.where("_id")
                                .is(
                                        new ObjectId(id.toString())
                                )
                )
        );

        if (gridFsFile == null) {
            return null;
        }

        return gridFsOperations.getResource(gridFsFile);
    }

    @Override
    public void deleteById(UUID id) {

    }
}
