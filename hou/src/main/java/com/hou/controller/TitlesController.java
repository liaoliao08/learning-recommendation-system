package com.hou.controller;

import com.hou.entity.Titles;
import com.hou.service.TitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/titles")
public class TitlesController {
    @Autowired
    private TitlesService titlesService;

    @GetMapping("/gettitle")
    public ResponseEntity<List<Titles>> getRandomTitles() {
        int countPerCategory = 6;
        List<Titles> titles = titlesService.getRandomTitlesByCategories(countPerCategory);
        return ResponseEntity.ok(titles);
    }

}
