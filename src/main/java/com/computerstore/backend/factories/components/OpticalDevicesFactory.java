package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.OpticalDevices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class OpticalDevicesFactory{

    public static OpticalDevices getOpticalDevices(String description, double price,int stock)
    {
        OpticalDevices cpu = new OpticalDevices.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}
