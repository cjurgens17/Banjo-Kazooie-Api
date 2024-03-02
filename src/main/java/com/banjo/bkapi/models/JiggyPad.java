package com.banjo.bkapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "jiggyPad")
public class JiggyPad extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word", insertable = false, updatable = false)
    @JsonIgnore
    private World world;

    @Column(name = "world_id")
    private Long worldId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_world", insertable = false,updatable = false)
    @JsonIgnore
    @JsonIgnoreProperties("jiggyPads")
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
