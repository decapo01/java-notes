package com.reachengine.notes.owners;

import com.reachengine.notes.common.Entity;

import java.time.LocalDateTime;

public class Owner implements Entity<OwnerId> {

    OwnerId id;

    String name;

    LocalDateTime updatedOn;

    public Owner(OwnerId id, String name, LocalDateTime updatedOn) {

        this.id = id;

        this.name = name;

        this.updatedOn = updatedOn;
    }

    @Override
    public OwnerId getId() {

        return this.id;
    }

    @Override
    public void setId(OwnerId id) {

        this.id = id;
    }

    public String getName(){

        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
