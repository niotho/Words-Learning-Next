package com.example.wordNext.controller;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.repository.WordRepository;
import com.example.wordNext.service.SourceService;
import com.example.wordNext.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private SourceService sourceService;
    private WordService wordService;

    @Autowired
    public HomeController(SourceService sourceService, WordService wordService) {
        this.sourceService = sourceService;
        this.wordService = wordService;
    }

    @GetMapping("/home")
    public String home(){

        return "home-page";
    }

    @GetMapping("/source")
    public String sourceList(Model model){

        List<Source> sources = sourceService.findAll();

        model.addAttribute("sources", sources);

        return "source/show-sources";
    }

    @GetMapping("/source/info/{id}")
    public String sourceInfo(Model model, @PathVariable("id") Long id){

        Source source = sourceService.findById(id);

        List<Word> words = wordService.findAllBySource(source);

        model.addAttribute("words", words);

        return "source/source-info";
    }

    @GetMapping("/word")
    public String wordList(Model model){

        List<Word> words = wordService.findAll();

        model.addAttribute("words", words);

        return "show-every-word";
    }

    @GetMapping({"/source/wordInfo/{id}", "/wordInfo/{id}"})
    public String wordInfo(Model model, @PathVariable("id") Long id){

        Source source = sourceService.findById(id);

        List<Word> words = wordService.findAllBySource(source);

        model.addAttribute("words", words);

        return "source/word-info";
    }

    @GetMapping("/source/createWord/{id}")
    public String showFormForAddWord(Model model, @PathVariable("id") Long id){

        Word word = new Word();

        model.addAttribute("word", word);
        model.addAttribute("sourceId", id);

        return "/source/create-word";
    }

    @PostMapping("/source/saveWord")
    public String saveWord(@RequestParam Long sourceId,
                           @RequestParam String wordName,
                           @RequestParam String wordMeaning,
                           @RequestParam String wordCategory,
                           @RequestParam String wordStatus,
                           @RequestParam String wordLOI){

        Source source = sourceService.findById(sourceId);

        Word newWord = new Word(wordName, wordMeaning, wordCategory, wordStatus, wordLOI, source);

        source.addWord(newWord);

        sourceService.save(source);

        return "redirect:/source/wordInfo/" + sourceId;
    }

    @GetMapping("/source/showUpdateWord")
    public String showUpdateWord(Model model, @RequestParam("id") Long wordId){

        Word word = wordService.findById(wordId);
        Long sourceId = word.getSource().getId();

        model.addAttribute("wordId", wordId);
        model.addAttribute("sourceId", sourceId);

        return "source/update-word";
    }

    @PostMapping("/source/updateWord")
    public String updateWord(@RequestParam("wordId") Long wordId,
                             @RequestParam("sourceId") Long sourceId,
                             @RequestParam("wordName") String wordName,
                             @RequestParam("wordMeaning") String wordMeaning,
                             @RequestParam("wordCategory") String wordCategory,
                             @RequestParam("wordStatus") String wordStatus,
                             @RequestParam("wordLOI") String wordLOI){

        Word word = wordService.findById(wordId);

        word.setWord(wordName);
        word.setMeaning(wordMeaning);
        word.setCategory(wordCategory);
        word.setStatus(wordStatus);
        word.setLevelOfImportance(wordLOI);

        Source source = sourceService.findById(sourceId);
        sourceService.save(source);

        return "redirect:/home";
    }

    @PostMapping("/source/deleteWord")
    public String deleteWord(@RequestParam("id") Long wordId){
        wordService.deleteWordById(wordId);
        return "redirect:/home";
    }

    @GetMapping("/source/createSource")
    public String showFormForAddSource(Model model){

        Source source = new Source();

        model.addAttribute("source", source);

        return "source/create-source";
    }

    @PostMapping("/source/saveSource")
    public String saveSource(@RequestParam String sourceGenre,
                             @RequestParam String sourceName){

        Source newSource = new Source(sourceGenre, sourceName);

        sourceService.save(newSource);

        return "redirect:/source";
    }

    @GetMapping("/source/showUpdateSource")
    public String showUpdateSource(Model model, @RequestParam("id") Long id){

        model.addAttribute("sourceId", id);

        return "source/update-source";
    }

    @PostMapping("/source/updateSource")
    public String updateSource(@RequestParam("sourceId") Long id,
                               @RequestParam("sourceGenre") String sourceGenre,
                               @RequestParam("sourceName") String sourceName){

        Source oldSource = sourceService.findById(id);

        oldSource.setGenre(sourceGenre);
        oldSource.setName(sourceName);
        sourceService.save(oldSource);

        return "redirect:/source";
    }

    @GetMapping("/source/deleteSource/{id}")
    public String deleteSource(@PathVariable("id") Long sourceId){

        sourceService.deleteSourceById(sourceId);

        return "redirect:/source";
    }
}






























