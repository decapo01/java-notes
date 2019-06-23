package com.reachengine.dahbashinotes.common;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

abstract public class AbstractInMemRepo<ID extends Id,ENTITY extends Entity<ID>, CRITERIA extends Criteria> implements Repository<ID,ENTITY,CRITERIA> {

    protected Map<ID,ENTITY> repo = new HashMap<>();

    protected ID nextId;

    @Override
    public Optional<ENTITY> findById(Id id) {

        ENTITY entityNullable = repo.get(id);

        return Optional.ofNullable(entityNullable);
    }

    public Optional<ENTITY> findByCriteria(CRITERIA criteria){

        return this.findAll(criteria).stream().findFirst();
    }

    abstract public List<ENTITY> findAll(CRITERIA criteria);

    @Override
    public ID insert(ENTITY entity) {

        ID _nextId = getNextId();

        entity.setId(_nextId);

        repo.put(_nextId,entity);

        return _nextId;
    }

    @Override
    public void update(ENTITY entity) {

        repo.replace(entity.getId(),entity);
    }

    @Override
    public void delete(ID id) {

        repo.remove(id);
    }

    abstract protected ID getNextId();
}
