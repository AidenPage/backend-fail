package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.OpticalDevices;
import com.computerstore.backend.repositories.components.OpticalDevicesRepository;
import com.computerstore.backend.services.components.OpticalDevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class OpticalDevicesServiceImpl implements OpticalDevicesService {
    @Autowired
    OpticalDevicesRepository repository;

    @Override
    public OpticalDevices create(OpticalDevices entity) {
        return repository.save(entity);
    }

    @Override
    public OpticalDevices readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<OpticalDevices> readAll() {
        Set<OpticalDevices> result = new HashSet<OpticalDevices>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public OpticalDevices update(OpticalDevices entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(OpticalDevices entity) {
        repository.delete(entity);

    } 
}
