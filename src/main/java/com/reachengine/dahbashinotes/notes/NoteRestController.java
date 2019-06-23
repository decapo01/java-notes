package com.reachengine.dahbashinotes.notes;


import com.reachengine.dahbashinotes.common.LocalDateTimeProvider;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/notes")
public class NoteRestController {

    NoteRepo noteRepo;

    LocalDateTimeProvider localDateTimeProvider;

    @Inject
    public NoteRestController(NoteRepo noteRepo, LocalDateTimeProvider localDateTimeProvider) {

        this.noteRepo = noteRepo;

        this.localDateTimeProvider = localDateTimeProvider;
    }

    @GetMapping
    List<NoteDto> index(NoteCriteria noteCriteria){

        return this.noteRepo.findAll(noteCriteria)
                   .stream()
                   .map(NoteDto::fromEntity)
                   .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    NoteDto get(@PathVariable("id") Integer id){

        return this.noteRepo
                   .findById(new NoteId(id))
                   .map(NoteDto::fromEntity)
                   .orElse(null);
    }


    @PostMapping
    NoteDto create(@Valid @RequestBody NoteDto noteDto){

        noteDto.setUpdatedOn(this.localDateTimeProvider.now());

        NoteId newId = this.noteRepo.insert(noteDto.toEntity());

        noteDto.setId(newId.item());

        return noteDto;
    }


    @PutMapping
    NoteDto update(@Valid @RequestBody NoteDto noteDto){

        noteDto.setUpdatedOn(this.localDateTimeProvider.now());

        return this.noteRepo
                   .findById(new NoteId(noteDto.id))
                   .map(note -> {

                       this.noteRepo.update(noteDto.toEntity());

                       return noteDto;
                   })
                   .orElseThrow(() -> new NotFoundException("Note Not Found"));
    }

    @DeleteMapping("/{id}")
    Boolean delete(@PathVariable("id") Integer id){

        this.noteRepo.delete(new NoteId(id));

        return true;
    }
}
