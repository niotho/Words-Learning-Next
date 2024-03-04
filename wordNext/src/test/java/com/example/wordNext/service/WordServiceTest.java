package com.example.wordNext.service;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.repository.WordRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WordServiceTest {

    @MockBean
    private WordRepository repository;

    @Autowired
    private WordService service;

    @Test
    public void Find_all_words(){

        List<Word> wordList = new ArrayList<>();
        Word word = new Word("word", "meaning", "category", "status", "LOI");
        Word word2 = new Word("word2", "meaning", "category", "status", "LOI");
        Word word3 = new Word("word3", "meaning", "category", "status", "LOI");
        wordList.add(word);
        wordList.add(word2);
        wordList.add(word3);

        when(repository.findAll()).thenReturn(wordList);
        List<Word> serviceWords = service.findAll();

        verify(repository, times(1)).findAll();
        assertEquals(3, serviceWords.size());
    }

    @Test
    public void Find_all_by_category(){

        List<Word> wordList = new ArrayList<>();
        Word word = new Word("word", "meaning", "category", "status", "LOI");
        Word word2 = new Word("word2", "meaning", "category", "status", "LOI");
        Word word3 = new Word("word3", "meaning", "category", "status", "LOI");
        wordList.add(word);
        wordList.add(word2);
        wordList.add(word3);

        when(repository.findByCategory("category")).thenReturn(wordList);
        List<Word> serviceWord = service.findAllByCategory("category");

        verify(repository, times(1)).findByCategory("category");
        assertEquals(3, serviceWord.size());
    }

    @Test
    public void Find_all_by_status(){

        List<Word> wordList = new ArrayList<>();
        Word word = new Word("word", "meaning", "category", "status", "LOI");
        Word word2 = new Word("word2", "meaning", "category", "status", "LOI");
        Word word3 = new Word("word3", "meaning", "category", "status", "LOI");
        wordList.add(word);
        wordList.add(word2);
        wordList.add(word3);

        when(repository.findByStatus("status")).thenReturn(wordList);
        List<Word> serviceWord = service.findAllByStatus("status");

        verify(repository, times(1)).findByStatus("status");
        assertEquals(3, serviceWord.size());
    }

    @Test
    public void Find_all_by_LOI(){

        List<Word> wordList = new ArrayList<>();
        Word word = new Word("word", "meaning", "category", "status", "LOI");
        Word word2 = new Word("word2", "meaning", "category", "status", "LOI");
        Word word3 = new Word("word3", "meaning", "category", "status", "LOI");
        wordList.add(word);
        wordList.add(word2);
        wordList.add(word3);

        when(repository.findByLevelOfImportance("LOI")).thenReturn(wordList);
        List<Word> serviceWord = service.findAllByLevelOfImportance("LOI");

        verify(repository, times(1)).findByLevelOfImportance("LOI");
        assertEquals(3, serviceWord.size());
    }

    @Test
    public void Find_word_by_id(){

        Long id = 1L;
        Word word = new Word("word3", "meaning", "category", "status", "LOI");

        when(repository.findById(id)).thenReturn(Optional.of(word));
        Word serviceWord = service.findById(id);

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void Find_by_word(){

        Word word = new Word("word", "meaning", "category", "status", "LOI");

        when(repository.findByWord("word")).thenReturn(word);
        Word serviceWord = service.findByWord("word");

        verify(repository, times(1)).findByWord("word");
    }

    @Test
    public void Save_word(){

        Word word = new Word("word", "meaning", "category", "status", "LOI");

        service.save(word);

        verify(repository, times(1)).save(word);
    }

    @Test
    public void Delete_word_by_id(){

        Long id = 1L;
        Word word = new Word("word", "meaning", "category", "status", "LOI");

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}