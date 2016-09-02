package com.computerstore.backend.services.Impl.Components;


import com.computerstore.backend.domain.components.CPU;
import com.computerstore.backend.repositories.components.CPURepository;
import com.computerstore.backend.services.components.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class CPUServiceImpl implements CPUService {
    @Autowired
    CPURepository repository;

    @Override
    public CPU create(CPU entity) {
        return repository.save(entity);
    }

    @Override
    public CPU readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<CPU> readAll() {
        Set<CPU> result = new HashSet<CPU>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public CPU update(CPU entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(CPU entity) {
        repository.delete(entity);

    }
}
