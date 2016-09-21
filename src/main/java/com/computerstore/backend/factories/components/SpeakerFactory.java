package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Speaker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class SpeakerFactory{
    public static Speaker getSpeaker(String description, double price,int stock)
    {
        Speaker cpu = new Speaker.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}

