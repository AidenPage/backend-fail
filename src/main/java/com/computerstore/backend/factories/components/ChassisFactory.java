package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Chassis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public class ChassisFactory{
    public static Chassis getChassis(String description, double price,int stock)
    {
        Chassis cpu = new Chassis.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }
}
