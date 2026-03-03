package com.hou.controller;
import com.hou.entity.MySearch;
import com.hou.service.SourcesService;
import com.hou.entity.Sources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hou.entity.PaginatedSources;
import com.hou.entity.MyDto;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/sources")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;

    @PostMapping("/getsourcebyname")
    public ResponseEntity<PaginatedSources> getSourcesByName(@RequestBody MySearch mySearch) {
        String name=mySearch.getname();
        int currentPage=mySearch.getcurrentPage();
        PaginatedSources paginatedSources = sourcesService.getSourcesByName(name, currentPage);
        return ResponseEntity.ok(paginatedSources);
    }


    @PostMapping("/getsource")
    public ResponseEntity<PaginatedSources> getSourcesByBelong(@RequestBody MyDto myDto) {
        String belong=myDto.getbelong();
        int page=myDto.getpage();
        PaginatedSources paginatedSources = sourcesService.getSourcesByBelong(belong, page);
        return ResponseEntity.ok(paginatedSources);
    }
}
