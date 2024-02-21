package com.example.wordNext.service;

import com.example.wordNext.entity.Source;
import com.example.wordNext.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceService {

    private SourceRepository sourceRepository;

    @Autowired
    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public Source findById(Long id){
        return sourceRepository.findById(id);
    }

    public List<Source> findAll(){
        return sourceRepository.findAll();
    }

    @Transactional
    public void save(Source source){
        sourceRepository.save(source);
    }

    @Transactional
    public void update(Source oldSource, Source newSource){

        if(newSource.getGenre() != null){
            oldSource.setGenre(newSource.getGenre());
        }
        if(newSource.getName() != null){
            oldSource.setName(newSource.getName());
        }

        sourceRepository.update(oldSource);
    }
}
