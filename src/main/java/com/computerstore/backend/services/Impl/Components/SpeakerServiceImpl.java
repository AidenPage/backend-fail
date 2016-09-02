package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Speaker;
import com.computerstore.backend.repositories.components.SpeakerRepository;
import com.computerstore.backend.services.components.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerRepository repository;

    @Override
    public Speaker create(Speaker entity) {
        return repository.save(entity);
    }

    @Override
    public Speaker readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Speaker> readAll() {
        Set<Speaker> result = new HashSet<Speaker>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Speaker update(Speaker entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Speaker entity) {
        repository.delete(entity);

    } 
}
