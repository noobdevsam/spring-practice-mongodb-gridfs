package com.example.springpracticemongodbgridfs.controllers;

import com.example.springpracticemongodbgridfs.services.GridFsService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("files")
public class GridFsController {

    private final GridFsService gridFsService;

    public GridFsController(GridFsService gridFsService) {
        this.gridFsService = gridFsService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "uploader", required = false) String uploader
    ) throws IOException {
        var id = gridFsService.store(file, uploader == null ? "anonymous" : uploader);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(
            @PathVariable String id
    ) throws IOException {
        var resource = gridFsService.getFileAsResource(id);
        if (resource == null) return ResponseEntity.notFound().build();

        var filename = resource.getFilename();
        var length = resource.contentLength();
        var contentType = resource.getContentType();


        return ResponseEntity.ok()
                .contentType(contentType == null ? MediaType.APPLICATION_OCTET_STREAM : MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentLength(length)
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        gridFsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
