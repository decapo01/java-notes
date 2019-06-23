package com.reachengine.notes.owners;


import com.reachengine.notes.common.LocalDateTimeProvider;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owners")
public class OwnerRestController {

    OwnerRepo ownerRepo;

    LocalDateTimeProvider localDateTimeProvider;

    @Inject
    public OwnerRestController(OwnerRepo             ownerRepo,
                               LocalDateTimeProvider localDateTimeProvider){

        this.ownerRepo = ownerRepo;

        this.localDateTimeProvider = localDateTimeProvider;
    }

    @GetMapping
    public List<OwnerDto> index(OwnerCriteria criteria){

        return this.ownerRepo
                   .findAll(criteria)
                   .stream()
                   .map(OwnerDto::mapFromEntity)
                   .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OwnerDto get(@PathVariable("id") Integer id){

        return this.ownerRepo
                   .findById(new OwnerId(id))
                   .map(OwnerDto::mapFromEntity)
                   .orElse(null);
    }


    @PostMapping
    public OwnerDto create(@Valid @RequestBody OwnerDto ownerDto){

        ownerDto.setUpdatedOn(this.localDateTimeProvider.now());

        OwnerId newId = this.ownerRepo.insert(ownerDto.mapToEntity());

        ownerDto.setId(newId.item());

        return ownerDto;
    }


    @PutMapping
    public OwnerDto update(@Valid @RequestBody OwnerDto ownerDto){

        ownerDto.setUpdatedOn(this.localDateTimeProvider.now());

        this.ownerRepo.update(ownerDto.mapToEntity());

        return ownerDto;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer id){

        OwnerId ownerId = new OwnerId(id);

        this.ownerRepo.delete(ownerId);

        return true;
    }


}
