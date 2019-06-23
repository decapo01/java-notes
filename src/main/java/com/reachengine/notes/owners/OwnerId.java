package com.reachengine.notes.owners;

import com.reachengine.notes.common.Id;

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
