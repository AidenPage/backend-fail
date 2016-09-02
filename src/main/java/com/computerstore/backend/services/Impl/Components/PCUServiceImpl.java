package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.PCU;
import com.computerstore.backend.repositories.components.PCURepository;
import com.computerstore.backend.services.components.PCUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class PCUServiceImpl implements PCUService {
    @Autowired
    PCURepository repository;

    @Override
    public PCU create(PCU entity) {
        return repository.save(entity);
    }

    @Override
    public PCU readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<PCU> readAll() {
        Set<PCU> result = new HashSet<PCU>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public PCU update(PCU entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(PCU entity) {
        repository.delete(entity);

    }
}
