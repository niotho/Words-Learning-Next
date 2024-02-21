package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;
import com.example.wordNext.entity.Word;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WordRepositoryImpl implements WordRepository{

    private EntityManager entityManager;

    @Autowired
    public WordRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Word findById(Long id) {
        return entityManager.find(Word.class, id);
    }

    @Override
    public List<Word> findAll() {

        TypedQuery<Word> query = entityManager.createQuery("From Word", Word.class);

        return query.getResultList();
    }

    @Override
    public List<Word> findAllWordsBySourceId(Long sourceId) {

        Source source = entityManager.find(Source.class, sourceId);

        TypedQuery<Word> query = entityManager.createQuery("SELECT w FROM Word w WHERE w.source = :source", Word.class);
        query.setParameter("source", source);
        List<Word> words = query.getResultList();

        return words;
    }

    @Transactional
    @Override
    public Word save(Word word) {



        return null;
    }

    @Transactional
    @Override
    public Word update(Word word) {
        return null;
    }
}
