package com.banjo.bkapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "honeycomb")
public class Honeycomb extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @JoinColumn(name = "world", updatable = false,insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties("honeycombs")
    private World world;

    @Column(name = "world_id")
    private Long worldId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_World", updatable = false,insertable = false)
    @JsonIgnore
    @JsonIgnoreProperties("honeycombs")
    private HubWorld hubWorld;

    @Column(name = "hubWorldId")
    private Long hubWorldId;


    @PostLoad
    public void postLoad(){
        if(hubWorld != null){
            hubWorldId = hubWorld.getId();
        }

        if(world != null){
            worldId = world.getId();
        }
    }
}
