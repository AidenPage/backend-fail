package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.DisplayCard;
import com.computerstore.backend.repositories.components.DisplayCardRepository;
import com.computerstore.backend.services.components.DisplayCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class DisplayCardServiceImpl implements DisplayCardService {
    @Autowired
    DisplayCardRepository repository;

    @Override
    public DisplayCard create(DisplayCard entity) {
        return repository.save(entity);
    }

    @Override
    public DisplayCard readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<DisplayCard> readAll() {
        Set<DisplayCard> result = new HashSet<DisplayCard>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public DisplayCard update(DisplayCard entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(DisplayCard entity) {
        repository.delete(entity);

    }
}
