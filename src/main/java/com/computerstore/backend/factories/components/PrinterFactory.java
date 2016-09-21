package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Printer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class PrinterFactory{

    public static Printer getPrinter(String description, double price,int stock)
    {
        Printer cpu = new Printer.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}

