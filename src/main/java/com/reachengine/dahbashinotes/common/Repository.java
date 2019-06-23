package com.reachengine.dahbashinotes.common;

import java.util.List;
import java.util.Optional;

public interface Repository<ID extends Id,ENTITY extends Entity<ID>, CRITERIA extends Criteria> {

    Optional<ENTITY> findById(ID id);

    Optional<ENTITY> findByCriteria(CRITERIA criteria);

    List<ENTITY> findAll(CRITERIA criteria);

    ID insert(ENTITY entity);

    void update(ENTITY entity);

    void delete(ID id);
}
