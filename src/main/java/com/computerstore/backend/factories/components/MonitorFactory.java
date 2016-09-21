package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Monitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class MonitorFactory{

    public static Monitor getMonitor(String description, double price,int stock)
    {
        Monitor cpu = new Monitor.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}

