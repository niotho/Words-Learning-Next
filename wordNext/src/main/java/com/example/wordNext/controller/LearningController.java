package com.example.wordNext.controller;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.service.SourceService;
import com.example.wordNext.service.WordService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequestMapping("/learning")
@Controller
public class LearningController {

    private SourceService sourceService;
    private WordService wordService;

    @Autowired
    public LearningController(SourceService sourceService, WordService wordService) {
        this.sourceService = sourceService;
        this.wordService = wordService;
    }

    @GetMapping("/repeatForm")
    public String showRepeatForm(Model model){

        List<Source> sources = sourceService.findAll();

        model.addAttribute("sources", sources);

        return "learning/learning-form";
    }

    @GetMapping("/repeat")
    public String showRepeatPage(Model model,
                                 @RequestParam(name = "wordSources", required = true) List<Long> sourceIds){

        LocalDateTime currentTime = LocalDateTime.now();

        List<Word> words = wordService.findFirstOldestAndStatusNotFinished(sourceIds);

        if(words.size() <= 0){
            return "learning/learning-blank";
        }

        Word oldestWord = words.get(0);


        for(Word eachWord : words){
            if(eachWord.getRepeatDate().isBefore(oldestWord.getRepeatDate())){
                oldestWord = eachWord;
            }
        }

        if(currentTime.isBefore(oldestWord.getRepeatDate())){
            return "learning/learning-blank";
        }

        model.addAttribute("word", oldestWord);
        model.addAttribute("sourceIds", sourceIds);

        return "learning/learning-word";
    }

    @PostMapping("/handleRepeat")
    public String handleRepeat(Model model,
                               @RequestParam("answer") String answer,
                               @RequestParam("wordId") Long wordId,
                               @RequestParam("wordSources") List<Long> sourceIds){


        Word word = wordService.findById(wordId);

        if("yes".equals(answer)){
            if(word.getStatus().equals("8")) {
                word.setStatus("finish");
                word.setRepeatDate(LocalDateTime.now());
                sourceService.save(word.getSource());
            }else if (Long.parseLong(word.getStatus()) >= 0 && Long.parseLong(word.getStatus()) < 8) {
                Long status = Long.parseLong(word.getStatus()) + 1;
                LocalDateTime repeatDate = word.getRepeatDate();
                LocalDateTime newRepeatDate = repeatDate.plusDays(daysToAdd(word.getStatus()));

                word.setStatus(status.toString());
                word.setRepeatDate(newRepeatDate);

                sourceService.save(word.getSource());
            }
        }

        if("no".equals(answer)){

            word.setStatus("0");
            word.setRepeatDate(LocalDateTime.now().minusHours(1));

            sourceService.save(word.getSource());
        }

        model.addAttribute("wordSources", sourceIds);

        return "redirect:/learning/repeatAgain";
    }

    @GetMapping("/repeatAgain")
    public String showRepeatPageAgain(Model model,
                                 @ModelAttribute("wordSources") List<Long> sourceIds){

        LocalDateTime currentTime = LocalDateTime.now();

        List<Word> words = wordService.findFirstOldestAndStatusNotFinished(sourceIds);

        if(words.size() <= 0){
            return "learning/learning-blank";
        }

        Word oldestWord = words.get(0);


        for(Word eachWord : words){
            if(eachWord.getRepeatDate().isBefore(oldestWord.getRepeatDate())){
                oldestWord = eachWord;
            }
        }

        if(currentTime.isBefore(oldestWord.getRepeatDate())){
            return "learning/learning-blank";
        }

        model.addAttribute("word", oldestWord);
        model.addAttribute("sourceIds", sourceIds);

        return "learning/learning-word";
    }

    @GetMapping("/list")
    public String showWords(Model model){

        List<Word> words = wordService.findAllByRepeatDate("finish");

        model.addAttribute("words", words);

        return "learning/learning-list";
    }

    @GetMapping("/finish")
    public String finishList(Model model){

        List<Word> words = wordService.findAllByStatus("finish");

        model.addAttribute("words", words);

        return "learning/finish-list";
    }

    @GetMapping("/reset")
    public String resetStatus(@RequestParam("id") Long id){

        Word word = wordService.findById(id);

        word.setStatus("0");
        word.setRepeatDate(LocalDateTime.now());

        wordService.save(word);

        return "redirect:/home";
    }

    private int daysToAdd(String status){

        int daysToAdd = switch (status){
            case "0" -> 1;
            case "1" -> 1;
            case "2" -> 1;
            case "3" -> 2;
            case "4" -> 3;
            case "5" -> 4;
            case "6" -> 7;
            case "7" -> 14;
            case "8" -> 28;
            case "finish" -> 0;
            default -> 0;
        };

        return daysToAdd;
    }
}
