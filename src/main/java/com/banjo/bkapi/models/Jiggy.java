package com.banjo.bkapi.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jiggy")
public class Jiggy extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "world", insertable = false,updatable = false)
    @JsonIgnore
    @JsonIgnoreProperties("jiggies")
    private World world;

    @Column(name = "world_id")
    private Long worldId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_world", insertable = false,updatable = false)
    @JsonIgnore
    @JsonIgnoreProperties("jiggies")
    private HubWorld hubWorld;

    @Column(name = "hub_world_id")
    private Long hubWorldId;

    @PostLoad
    public void postLoad(){
        if(world != null){
            worldId = world.getId();
        }

        if(hubWorld != null){
            hubWorldId = hubWorld.getId();
        }
    }
}
