package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Memory;
import com.computerstore.backend.repositories.components.MemoryRepository;
import com.computerstore.backend.services.components.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class MemoryServiceImpl implements MemoryService {
    @Autowired
    MemoryRepository repository;

    @Override
    public Memory create(Memory entity) {
        return repository.save(entity);
    }

    @Override
    public Memory readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Memory> readAll() {
        Set<Memory> result = new HashSet<Memory>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Memory update(Memory entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Memory entity) {
        repository.delete(entity);

    }
}
