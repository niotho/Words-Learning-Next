package com.example.wordNext.repository;

import com.example.wordNext.entity.Word;

import java.util.List;

public interface WordRepository {

    //find
    Word findById(Long id);
    List<Word> findAll();
    List<Word> findAllWordsBySourceId(Long sourceId);

    //save
    Word save(Word word);

    //update
    Word update(Word word);

    //delete
    void delete(Word word);
}
