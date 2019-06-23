package com.reachengine.notes.notes;

import com.reachengine.notes.common.Id;

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
