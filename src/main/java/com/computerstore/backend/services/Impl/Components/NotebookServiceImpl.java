package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Notebook;
import com.computerstore.backend.repositories.components.NotebookRepository;
import com.computerstore.backend.services.components.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class NotebookServiceImpl implements NotebookService {
    @Autowired
    NotebookRepository repository;

    @Override
    public Notebook create(Notebook entity) {
        return repository.save(entity);
    }

    @Override
    public Notebook readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Notebook> readAll() {
        Set<Notebook> result = new HashSet<Notebook>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Notebook update(Notebook entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Notebook entity) {
        repository.delete(entity);

    }
}
