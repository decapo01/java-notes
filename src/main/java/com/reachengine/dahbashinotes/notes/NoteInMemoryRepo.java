package com.reachengine.dahbashinotes.notes;


import com.reachengine.dahbashinotes.common.AbstractInMemRepo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class NoteInMemoryRepo extends AbstractInMemRepo<NoteId,Note,NoteCriteria> implements NoteRepo {


    public NoteInMemoryRepo(){}


    NoteId nextId = new NoteId(0);

    public List<Note> findAll(NoteCriteria criteria){

        // Functional Approach

        /*
        List<Note> notes =
            this.repo.entrySet()
            .stream()
            .filter(i -> testCriteria(i.getValue(),criteria))
            .map(e -> e.getValue())
            .collect(Collectors.toList());
        */


        // Imperative Approach

        List<Note> notes = new ArrayList<>();

        for(Map.Entry<NoteId,Note> noteEntry : this.repo.entrySet()){

            if(testCriteria(noteEntry.getValue(),criteria)){

                notes.add(noteEntry.getValue());
            }
        }

        return notes;
    }


    Boolean testCriteria(Note note, NoteCriteria criteria){

        // Functional approach
        return Stream.of(

            /*if*/ criteria.query != null ?
                note.getBody().toLowerCase().contains(criteria.query.toLowerCase())
            /*el*/:
                null
        )
        .filter(x -> x != null)
        .reduce(true,(a,b) -> a && b);


          // Imperative approach

          /*
          Boolean condition = true;

          if(criteria.searchTerm != null){

              condition = note.getBody().toLowerCase().contains(criteria.query.toLowerCase());
          }

          return condition;
          */
    }


    protected NoteId getNextId(){

        this.nextId = new NoteId(this.nextId.item() + 1);

        return this.nextId;
    }
}
