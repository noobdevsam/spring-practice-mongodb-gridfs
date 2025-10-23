package com.example.springpracticemongodbgridfs.controllers;

import com.example.springpracticemongodbgridfs.services.GridFsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
