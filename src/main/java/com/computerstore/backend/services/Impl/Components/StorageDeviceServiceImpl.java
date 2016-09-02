package com.computerstore.backend.services.Impl.Components;

import com.computerstore.backend.domain.components.StorageDevice;
import com.computerstore.backend.repositories.components.StorageDeviceRepository;
import com.computerstore.backend.services.components.StorageDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Aidem on 2016/04/17.
 */
@Service
public class StorageDeviceServiceImpl implements StorageDeviceService {
    @Autowired
    StorageDeviceRepository repository;

    @Override
    public StorageDevice create(StorageDevice entity) {
        return repository.save(entity);
    }

    @Override
    public StorageDevice readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<StorageDevice> readAll() {
        Set<StorageDevice> result = new HashSet<StorageDevice>();
        while (repository.findAll().iterator().hasNext()) {
            result.add(repository.findAll().iterator().next());
        }
        return result;
    }
    @Override
    public StorageDevice update(StorageDevice entity) {
        return repository.save(entity);
    }
    @Override
    public void delete(StorageDevice entity) {
        repository.delete(entity);

    }
}
