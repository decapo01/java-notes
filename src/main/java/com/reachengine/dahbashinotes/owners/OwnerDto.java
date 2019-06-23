package com.reachengine.dahbashinotes.owners;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class OwnerDto {

    Integer id;

    @NotEmpty
    String name;

    @Null
    LocalDateTime updatedOn;

    public OwnerDto(Integer id, @NotEmpty String name, @Null LocalDateTime updatedOn) {

        this.id = id;
        this.name = name;
        this.updatedOn = updatedOn;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public static OwnerDto mapFromEntity(Owner owner){

        return new OwnerDto(owner.getId().item(),owner.getName(),owner.getUpdatedOn());
    }

    public Owner mapToEntity(){

        Integer id = this.id == null ? 0 : this.id;

        OwnerId ownerId = new OwnerId(id);

        return new Owner(ownerId,this.name,this.updatedOn);
    }
}
