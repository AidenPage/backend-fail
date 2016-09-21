package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.PCU;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class PCUFactory{

    public static PCU getPCU(String description, double price,int stock)
    {
        PCU cpu = new PCU.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}
