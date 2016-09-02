package com.computerstore.backend.repositories.components;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.computerstore.backend.domain.components.Memory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends CrudRepository<Memory,Long>{


}
