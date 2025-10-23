package com.example.springpracticemongodbgridfs.services.impl;

import com.example.springpracticemongodbgridfs.services.GridFsService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GridFsServiceImplTest {

    private GridFsTemplate gridFsTemplate;
    private GridFsOperations gridFsOperations;
    private GridFsService service;

    @BeforeEach
    void setUp() {
        gridFsTemplate = mock(GridFsTemplate.class);
        gridFsOperations = mock(GridFsOperations.class);
        service = new GridFsServiceImpl(gridFsTemplate, gridFsOperations);
    }

    @Test
    void store_shouldStoreFileAndReturnId() throws IOException {
        var file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("test.txt");
        when(file.getContentType()).thenReturn("text/plain");
        when(file.getSize()).thenReturn(123L);
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream("data".getBytes()));

        var objectId = new ObjectId();
        when(gridFsTemplate.store(any(), anyString(), anyString(), any(Document.class)))
                .thenReturn(objectId);

        var result = service.store(file, "uploader1");
        assertEquals(objectId.toHexString(), result);

        var metaCaptor = ArgumentCaptor.forClass(Document.class);
        verify(gridFsTemplate).store(any(), eq("test.txt"), eq("text/plain"), metaCaptor.capture());

        var meta = metaCaptor.getValue();
        assertEquals("uploader1", meta.get("uploader"));
        assertEquals("test.txt", meta.get("originalFilename"));
        assertEquals(123L, meta.get("size"));
    }

    @Test
    void store_shouldThrowIOException() throws IOException {
        var file = mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException("fail"));

        assertThrows(IOException.class, () -> service.store(file, "uploader1"));
    }

    @Test
    void getFileAsResource_shouldReturnResource() {
        var hexId = new ObjectId().toHexString();
        var gridFsFile = mock(com.mongodb.client.gridfs.model.GridFSFile.class);
        var resource = mock(GridFsResource.class);

        when(gridFsTemplate.findOne(any(Query.class))).thenReturn(gridFsFile);
        when(gridFsOperations.getResource(gridFsFile)).thenReturn(resource);

        var result = service.getFileAsResource(hexId);

        assertEquals(resource, result);
        verify(gridFsTemplate).findOne(any(Query.class));
        verify(gridFsOperations).getResource(gridFsFile);
    }

    @Test
    void deleteById_shouldDeleteFile() {
        var hexId = new ObjectId().toHexString();
        service.deleteById(hexId);

        var queryCaptor = ArgumentCaptor.forClass(Query.class);
        verify(gridFsTemplate).delete(queryCaptor.capture());

        var query = queryCaptor.getValue();
        assertNotNull(query);
    }
}