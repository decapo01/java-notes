package com.reachengine.dahbashinotes.notes;

import com.reachengine.dahbashinotes.common.Id;

import java.io.Serializable;

public class NoteId implements Id<Integer> {

    private int item;

    public NoteId(int item){

        this.item = item;
    }

    @Override
    public Integer item() {

        return this.item;
    }

//    @Override
//    public Boolean equals(NoteId noteId){
//
//        return this.item == noteId.item;
//    }

    @Override
    public boolean equals(Object object){

        NoteId other = (NoteId) object;

        return this.item == other.item;
    }

    @Override
    public int hashCode(){

        return this.item;
    }
}
