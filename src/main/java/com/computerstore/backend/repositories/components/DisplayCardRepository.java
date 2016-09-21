package com.computerstore.backend.repositories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import com.computerstore.backend.domain.components.DisplayCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayCardRepository extends CrudRepository<DisplayCard,Long>{


}
