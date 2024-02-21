package com.example.wordNext.controller;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.service.SourceService;
import com.example.wordNext.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForAddSource")
    public String showFormForAddSource(Model model){

        Source source = new Source();

        model.addAttribute("source", source);

        return "add-source";
    }

//    @GetMapping("/showFormForUpdate")
//    public String update(Model model, @RequestParam Long sourceId){
//
//        Source oldSource = sourceService.findById(sourceId);
//        Source newSource = new Source();
//
//        model.addAttribute("oldSource", oldSource);
//        model.addAttribute("newSource", newSource);
//
//        return "update-source";
//    }

    @PostMapping("/save")
    public String save(@ModelAttribute Source source){

        sourceService.save(source);

        return "redirect:/home";
    }

//    @PostMapping("/update")
//    public String update(@ModelAttribute Source oldSource, @ModelAttribute Source newSource){
//
//        sourceService.update(oldSource, newSource);
//
//        return "redirect:/home";
//    }

    @GetMapping("/source/{sourceId}")
    public String source(@PathVariable("sourceId") Long id, Model model){

        List<Word> words = wordService.findAllWordsBySourceId(id);

        model.addAttribute("sourceId", id);
        model.addAttribute("words", words);

        return "source-page";
    }

    @PostMapping("/source/save")
    public String save(Model model,
                       @RequestParam String wordName,
                       @RequestParam String wordMeaning,
                       @RequestParam String wordStatus,
                       @RequestParam String wordLOI,
                       @RequestParam Long wordSourceId){

        Source source = sourceService.findById(wordSourceId);

        Word word = new Word(wordName, wordMeaning, wordStatus, wordLOI, source);

        wordService.save(word, source);

        return "redirect:/home";
    }
}












