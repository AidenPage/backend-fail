package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Monitor;
import com.computerstore.backend.repositories.components.MonitorRepository;
import com.computerstore.backend.services.components.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class MonitorServiceImpl implements MonitorService {
    @Autowired
    MonitorRepository repository;

    @Override
    public Monitor create(Monitor entity) {
        return repository.save(entity);
    }

    @Override
    public Monitor readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Monitor> readAll() {
        Set<Monitor> result = new HashSet<Monitor>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Monitor update(Monitor entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Monitor entity) {
        repository.delete(entity);

    }
}
