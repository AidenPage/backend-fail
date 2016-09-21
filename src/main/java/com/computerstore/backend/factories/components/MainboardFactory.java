package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Mainboard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class MainboardFactory{
    public static Mainboard getMainboard(String description, double price,int stock)
    {
        Mainboard cpu = new Mainboard.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }
}

