package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.repository.SourceRepository;
import com.example.wordNext.repository.WordRepository;
import lombok.Data;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.Random.class)
public class RepositoryTest {

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void SourceRepository_find_all_by_genre(){

        Source source = new Source("books", "koi");
        sourceRepository.save(source);

        List<Source> findSource = sourceRepository.findAllByGenre("books");

        assertThat(findSource).isNotNull();
    }

    @Test
    public void SourceRepository_find_by_name(){

        Source source = new Source("books", "koi");
        sourceRepository.save(source);

        Source findSource = sourceRepository.findByName("koi");

        Assertions.assertThat(findSource).isNotNull();
    }

    @Test
    public void WordRepository_find_by_word(){

        Word word = new Word("word", "meaning", "category", "ok", "loi");
        wordRepository.save(word);

        Word findWord = wordRepository.findByWord("word");

        assertThat(findWord).isNotNull();
    }

    @Test
    public void WordRepository_find_by_category(){

        Word word = new Word("word", "meaning", "category", "ok", "loi");
        wordRepository.save(word);

        List<Word> findWord = wordRepository.findByCategory("category");

        assertThat(findWord).isNotNull();
    }

    @Test
    public void WordRepository_find_by_status(){

        Word word = new Word("word", "meaning", "category", "ok", "loi");
        wordRepository.save(word);

        List<Word> findWord = wordRepository.findByStatus("ok");

        assertThat(findWord).isNotNull();
    }

    @Test
    public void WordRepository_find_by_LOI(){

        Word word = new Word("word", "meaning", "category", "ok", "loi");
        wordRepository.save(word);
        Word word2 = new Word("word2", "meaning2", "category2", "ok2", "loi");
        wordRepository.save(word2);

        List<Word> newList = new ArrayList<>();
        newList.add(word);
        newList.add(word2);

        List<Word> findWord = wordRepository.findByLevelOfImportance("loi");

        assertThat(findWord).hasSize(2);
        Assertions.assertThat(findWord).isEqualTo(newList);
    }
}