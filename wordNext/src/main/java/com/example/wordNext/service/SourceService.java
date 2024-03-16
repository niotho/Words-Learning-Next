package com.example.wordNext.service;

import com.example.wordNext.entity.Source;
import com.example.wordNext.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SourceService {

    private SourceRepository sourceRepository;

    @Autowired
    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public List<Source> findAll(){
        return sourceRepository.findAll();
    }
    public List<Source> findAllByGenre(String genre) {
        return sourceRepository.findAllByGenre(genre);
    }
    public Source findById(Long id){
        return sourceRepository.findById(id).orElseThrow();
    }
    public Source findByName(String name){
        return sourceRepository.findByName(name);
    }

    public boolean findIfExistsById(Long id){

        Optional<Source> source = sourceRepository.findById(id);

        if(source.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public Source save(Source source){
        return sourceRepository.save(source);
    }

    @Transactional
    public void deleteSourceById(Long id){
        sourceRepository.deleteSourceById(id);
    }
}
