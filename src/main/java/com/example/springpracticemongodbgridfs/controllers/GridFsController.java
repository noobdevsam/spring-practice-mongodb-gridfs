package com.example.springpracticemongodbgridfs.controllers;

import com.example.springpracticemongodbgridfs.services.GridFsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("files")
public class GridFsController {

    private final GridFsService gridFsService;

    public GridFsController(GridFsService gridFsService) {
        this.gridFsService = gridFsService;
    }

}
