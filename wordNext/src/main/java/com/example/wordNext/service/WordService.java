package com.example.wordNext.service;

import com.example.wordNext.entity.Word;
import com.example.wordNext.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private WordRepository repository;

    @Autowired
    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public List<Word> findAll(){
        return repository.findAll();
    }
    public List<Word> findAllByCategory(String category){
        return repository.findByCategory(category);
    }
    public List<Word> findAllByStatus(String status){
        return repository.findByStatus(status);
    }
    public List<Word> findAllByLevelOfImportance(String LOI){
        return repository.findByLevelOfImportance(LOI);
    }
    public Word findById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public Word findByWord(String word){
        return repository.findByWord(word);
    }
    public boolean findIfExistsById(Long id){

        Optional<Word> word = repository.findById(id);

        if(word.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public Word save(Word word){
        return repository.save(word);
    }

    public boolean deleteById(Long id){

        Optional<Word> word = repository.findById(id);

        repository.deleteById(id);

        if(word.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}