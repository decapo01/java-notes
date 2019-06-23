package com.reachengine.dahbashinotes.owners;

import com.reachengine.dahbashinotes.common.Id;

public class OwnerId implements Id<Integer> {

    Integer item;

    public OwnerId(Integer item) {
        this.item = item;
    }

    @Override
    public Integer item() {
        return this.item;
    }
}
