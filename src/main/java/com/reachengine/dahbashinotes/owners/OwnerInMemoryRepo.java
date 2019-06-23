package com.reachengine.dahbashinotes.owners;

import com.reachengine.dahbashinotes.common.AbstractInMemRepo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class OwnerInMemoryRepo extends AbstractInMemRepo<OwnerId,Owner,OwnerCriteria> implements OwnerRepo {

    OwnerId nextId = new OwnerId(0);

    @Override
    public List<Owner> findAll(OwnerCriteria criteria) {

        return
            this.repo.entrySet()
            .stream()
            .filter(e -> testCriteria(e.getValue(),criteria))
            .map(e -> e.getValue())
            .collect(Collectors.toList());

    }

    @Override
    protected OwnerId getNextId() {

        this.nextId = new OwnerId(this.nextId.item + 1);

        return this.nextId;
    }

    boolean testCriteria(Owner owner, OwnerCriteria criteria){

        return Stream.of(

            /*if*/ criteria.query != null ?
                owner.getName().toLowerCase().contains(criteria.query)
            /*el*/:
                null
        )
        .filter(x -> x != null)
        .reduce(true,(a,b) -> a && b);
    }
}
