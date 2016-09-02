package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.Printer;
import com.computerstore.backend.repositories.components.PrinterRepository;
import com.computerstore.backend.services.components.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class PrinterServiceImpl implements PrinterService {
    @Autowired
    PrinterRepository repository;

    @Override
    public Printer create(Printer entity) {
        return repository.save(entity);
    }

    @Override
    public Printer readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Printer> readAll() {
        Set<Printer> result = new HashSet<Printer>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public Printer update(Printer entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(Printer entity) {
        repository.delete(entity);

    } 
}
