package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.DisplayCard;
import org.springframework.data.repository.CrudRepository;


public class DisplayCardFactory{

    public static DisplayCard getDisplayCard(String description, double price,int stock)
    {
        DisplayCard cpu = new DisplayCard.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }
}
