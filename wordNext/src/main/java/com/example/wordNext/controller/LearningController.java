package com.example.wordNext.controller;

import com.example.wordNext.entity.Word;
import com.example.wordNext.service.SourceService;
import com.example.wordNext.service.WordService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/repeat")
    public String showRepeatPage(Model model){

        LocalDateTime currentTime = LocalDateTime.now();
        Word word = wordService.findFirstByRepeatDate();

        if(currentTime.isBefore(word.getRepeatDate())){
            return "learning/learning-blank";
        }

        model.addAttribute("word", word);

        return "learning/learning-word";
    }

    @PostMapping("/handleRepeat")
    public String handleRepeat(@RequestParam("answer") String answer,
                               @RequestParam("wordId") Long wordId){

        Word word = wordService.findById(wordId);

        if("yes".equals(answer)){
            if(Long.parseLong(word.getStatus()) >= 0 && Long.parseLong(word.getStatus()) < 12){
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

        return "redirect:/learning/repeat";
    }

    @GetMapping("/list")
    public String showWords(Model model){

        List<Word> words = wordService.findAll();

        model.addAttribute("words", words);

        return "learning/learning-list";
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
