package com.reachengine.notes.common;

public interface Entity<ID extends Id> {

    ID getId();

    void setId(ID id);
}
