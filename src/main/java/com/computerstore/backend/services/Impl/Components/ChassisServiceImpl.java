package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Chassis;
import com.computerstore.backend.repositories.components.ChassisRepository;
import com.computerstore.backend.services.components.ChassisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class ChassisServiceImpl implements ChassisService {
    @Autowired
    ChassisRepository repository;

    @Override
    public Chassis create(Chassis entity) {
        return repository.save(entity);
    }

    @Override
    public Chassis readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Chassis> readAll() {
        Set<Chassis> result = new HashSet<Chassis>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Chassis update(Chassis entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Chassis entity) {
        repository.delete(entity);

    } 
}
