package com.reachengine.dahbashinotes.notes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class NoteDto {

    Integer id;

    @NotEmpty
    String body;

    @Null
    LocalDateTime updatedOn;

    public NoteDto(){}

    public NoteDto(@NotNull Integer id, @NotEmpty String body, @Null LocalDateTime updatedOn) {
        this.id = id;
        this.body = body;
        this.updatedOn = updatedOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Note toEntity(){

        NoteId noteId = new NoteId(this.id == null ? 0 : this.id);

        return new Note(noteId,this.body,this.updatedOn);
    }

    public static NoteDto fromEntity(Note note){

        return new NoteDto(note.getId().item(),note.getBody(),note.getUpdatedOn());
    }
}
