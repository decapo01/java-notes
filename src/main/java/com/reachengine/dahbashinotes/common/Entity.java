package com.reachengine.dahbashinotes.common;

public interface Entity<ID extends Id> {

    ID getId();

    void setId(ID id);
}
