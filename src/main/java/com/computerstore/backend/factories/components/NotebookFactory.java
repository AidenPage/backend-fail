package com.computerstore.backend.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.repositories.components.*;
import com.computerstore.backend.domain.components.Notebook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public class NotebookFactory{
    public static Notebook getNotebook(String description, double price,int stock)
    {
        Notebook cpu = new Notebook.Builder()
                .description(description)
                .price(price)
                .stock(stock)
                .build();
        return cpu;
    }

}
