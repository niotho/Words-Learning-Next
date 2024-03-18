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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long>{

    Word findByWord(String word);
    Word findFirstByOrderByRepeatDateAsc();

    List<Word> findByCategory(String category);

    List<Word> findByStatus(String status);

    List<Word> findByLevelOfImportance(String LOI);

    List<Word> findBySource(Source source);

    List<Word> findAllByOrderByRepeatDateDesc();

    @Modifying
    @Query(value = "DELETE FROM word_list WHERE id = :id", nativeQuery = true)
    void deleteWordById(Long id);
}
