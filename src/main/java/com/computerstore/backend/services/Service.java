package com.computerstore.backend.services;

import java.util.Set;


/**
 * Created by Aidem on 2016/04/17.
 */

public interface Service<E, ID> {

    E create(E entity);

    E readById(ID id);

    Set<E> readAll();

    E update(E entity);

    void delete(E entity);


}