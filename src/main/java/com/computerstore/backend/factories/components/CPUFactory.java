package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.domain.components.CPU;

public class CPUFactory{

    public static CPU getCPU(String description, double price,int stock)
    {
        CPU cpu = new CPU.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }
}