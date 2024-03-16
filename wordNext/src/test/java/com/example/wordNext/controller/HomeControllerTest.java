package com.example.wordNext.controller;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import com.example.wordNext.service.SourceService;
import com.example.wordNext.service.WordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SourceService sourceService;

    @MockBean
    private WordService wordService;

    @BeforeEach
    public void beforeEach(){
        jdbcTemplate.execute("INSERT INTO shows (genre, show_name) VALUES ('anime', 'cowBoyBeBop')");
        jdbcTemplate.execute("INSERT INTO word_list (word, meaning, category, status, LOI) VALUES ('lol', 'laugh', 'meme', 'ok', 'not really')");
    }

    @AfterEach
    public void afterEach(){
        jdbcTemplate.execute("DELETE FROM shows");
        jdbcTemplate.execute("DELETE FROM word_list");
    }

    @Test
    public void home() throws Exception {

        Source source = new Source("anime", "cowBoyBeBop");
        Word word = new Word("lol", "laugh", "meme", "ok", "not really");

        List<Source> sourceList = new ArrayList<>();
        List<Word> wordList = new ArrayList<>();

        sourceList.add(source);
        wordList.add(word);

        when(sourceService.findAll()).thenReturn(sourceList);
        when(wordService.findAll()).thenReturn(wordList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sources", sourceList))
                .andExpect(model().attribute("words", wordList))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView, "home-page");
    }
}
