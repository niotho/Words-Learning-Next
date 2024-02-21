package com.example.wordNext.repository;

import com.example.wordNext.entity.Source;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class SourceRepositoryImpl implements SourceRepository{

    private EntityManager entityManager;

    @Autowired
    public SourceRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Source findById(Long id) {
        return entityManager.find(Source.class, id);
    }

    @Override
    public List<Source> findAll() {

        TypedQuery<Source> query = entityManager.createQuery("FROM Source", Source.class);

        List<Source> sources = query.getResultList();

        return sources;
    }

    @Override
    public void save(Source source) {
        entityManager.persist(source);
    }

    @Override
    public void update(Source source) {
        entityManager.merge(source);
    }
}
