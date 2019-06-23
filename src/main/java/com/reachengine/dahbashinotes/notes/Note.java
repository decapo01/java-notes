package com.reachengine.dahbashinotes.notes;


import com.reachengine.dahbashinotes.common.Entity;

import java.time.LocalDateTime;

public class Note implements Entity<NoteId> {

    private NoteId id;

    private String body;

    private LocalDateTime updatedOn;

    public Note(NoteId id, String body, LocalDateTime updatedOn){

        this.id = id;

        this.body = body;

        this.updatedOn = updatedOn;
    }

    @Override
    public NoteId getId() {

        return this.id;
    }

    @Override
    public void setId(NoteId id) {

        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
