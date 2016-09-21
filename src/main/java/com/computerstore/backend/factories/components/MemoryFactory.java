package com.computerstore.backend.factories.components;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Memory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class MemoryFactory{
    public static Memory getMemory(String description, double price,int stock)
    {
        Memory cpu = new Memory.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}
