package com.example.wordNext.service;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.repository.SourceRepository;
import com.example.wordNext.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private WordRepository wordRepository;
    private SourceRepository sourceRepository;

    @Autowired
    public WordService(WordRepository wordRepository, SourceRepository sourceRepository) {
        this.wordRepository = wordRepository;
        this.sourceRepository = sourceRepository;
    }

    public Word findById(Long id){
        return wordRepository.findById(id);
    }

    public List<Word> findAll(){
        return wordRepository.findAll();
    }

    public List<Word> findAllWordsBySourceId(Long sourceId){
        return wordRepository.findAllWordsBySourceId(sourceId);
    }

    //save
    public void save(Word word, Source source){

        word.setSource(source);
        source.addWord(word);

        sourceRepository.save(source);
    }
}

















