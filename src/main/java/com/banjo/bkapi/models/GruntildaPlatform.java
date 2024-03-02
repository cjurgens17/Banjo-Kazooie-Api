package com.banjo.bkapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gruntildaPlatform")
public class GruntildaPlatform extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "world", insertable = false,updatable = false)
    @JsonIgnore
    private World world;

    @Column(name = "world_id")
    private Long worldId;

    @PostLoad
    public void postLoad(){
        if(world != null){
            worldId = world.getId();
        }
    }
}
