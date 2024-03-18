//package com.example.wordNext.service;
//
//import com.example.wordNext.entity.Source;
//import com.example.wordNext.repository.SourceRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//public class SourceServiceTest {
//
//    @MockBean
//    private SourceRepository repository;
//
//    @Autowired
//    private SourceService service;
//
//    @Test
//    public void Find_all_sources(){
//
//        List<Source> sourceList = new ArrayList<>();
//        Source source = new Source("books", "koi");
//        Source source2 = new Source("anime", "mei");
//        sourceList.add(source);
//        sourceList.add(source2);
//
//        when(repository.findAll()).thenReturn(sourceList);
//        List<Source> serviceList = service.findAll();
//
//        verify(repository, times(1)).findAll();
//        assertEquals(2, serviceList.size());
//    }
//
//    @Test
//    public void Find_all_by_genre(){
//
//        List<Source> sourceList = new ArrayList<>();
//        Source source = new Source("books", "koi");
//        Source source2 = new Source("books", "lop");
//        sourceList.add(source);
//        sourceList.add(source2);
//
//        when(repository.findAllByGenre("books")).thenReturn(sourceList);
//        List<Source> serviceSources = service.findAllByGenre("books");
//
//        verify(repository, times(1)).findAllByGenre("books");
//        Assertions.assertThat(serviceSources).hasSize(2);
//
//    }
//
//    @Test
//    public void Find_source_by_id(){
//
//        Source source = new Source("books", "koi");
//
//        when(repository.findById(1L)).thenReturn(Optional.of(source));
//        Source serviceSource = service.findById(1L);
//
//        verify(repository, times(1)).findById(1L);
//        Assertions.assertThat(serviceSource).isNotNull();
//    }
//
//    @Test
//    public void Find_source_by_name(){
//
//        Source source = new Source("books", "koi");
//
//        when(repository.findByName("koi")).thenReturn(source);
//        Source serviceSource = service.findByName("koi");
//
//        verify(repository, times(1)).findByName("koi");
//        Assertions.assertThat(serviceSource).isNotNull();
//    }
//
////    @Test
////    public void Find_if_source_exists(){
////
////    }
//
//    @Test
//    public void Save_source(){
//
//        Source source = new Source("books", "koi");
//
//        service.save(source);
//
//        verify(repository, times(1)).save(source);
//    }
//
//    @Test
//    public void Delete_source(){
//
//        Long id = 1L;
//
//        service.deleteById(id);
//
//        verify(repository, times(1)).deleteById(1L);
//    }
//}
