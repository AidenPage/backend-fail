package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Mainboard;
import com.computerstore.backend.repositories.components.MainboardRepository;
import com.computerstore.backend.services.components.MainboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class MainboardServiceImpl implements MainboardService {
    @Autowired
    MainboardRepository repository;

    @Override
    public Mainboard create(Mainboard entity) {
        return repository.save(entity);
    }

    @Override
    public Mainboard readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Mainboard> readAll() {
        Set<Mainboard> result = new HashSet<Mainboard>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Mainboard update(Mainboard entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Mainboard entity) {
        repository.delete(entity);

    }
}
