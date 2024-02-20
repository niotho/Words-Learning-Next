package com.example.wordNext.controller;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.service.SourceService;
import com.example.wordNext.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private SourceService sourceService;
    private WordService wordService;

    @Autowired
    public MainController(SourceService sourceService, WordService wordService) {
        this.sourceService = sourceService;
        this.wordService = wordService;
    }

    @GetMapping("/home")
    public String home(Model model){

        List<Source> sourceList = sourceService.findAll();

        model.addAttribute("sources", sourceList);

        return "home-page";
    }

    @GetMapping("/source/{sourceId}")
    public String source(@PathVariable("sourceId") Long id, Model model){

        List<Word> words = wordService.findAllWordsBySourceId(id);

        model.addAttribute("sourceId", id);
        model.addAttribute("words", words);

        return "source-page";
    }

    @PostMapping("/source/save")
    public String save(@RequestParam)
}
