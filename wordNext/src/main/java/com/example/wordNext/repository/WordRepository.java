package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long>{

    Word findByWord(String word);

    List<Word> findByCategory(String category);

    List<Word> findByStatus(String status);

    List<Word> findByLevelOfImportance(String LOI);

    List<Word> findBySource(Source source);

    @Modifying
    @Query(value = "DELETE FROM word_list WHERE id = :id", nativeQuery = true)
    void deleteWordById(Long id);
}
