package com.computerstore.backend.repositories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.domain.components.Monitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends CrudRepository<Monitor,Long>{



}

