package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;

import java.util.List;

public interface SourceRepository {

    //find
    Source findById(Long id);
    List<Source> findAll();

    //save
    void save(Source source);

    //update
    void update(Source source);
}
