package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long>{

    List<Source> findAllByGenre(String genre);
    Source findByName(String name);

    void deleteSourceById(Long id);
}
