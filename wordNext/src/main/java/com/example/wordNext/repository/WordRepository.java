package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long>{

//    @Query("FROM Word w WHERE w.word = :word")
    Word findByWord(String word);

    List<Word> findByCategory(String category);

    List<Word> findByStatus(String status);

    List<Word> findByLevelOfImportance(String LOI);
}
