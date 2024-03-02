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
@Table(name = "door")
public class Door  extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @Column(name = "required_notes")
    private int requiredNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_world",insertable = false,updatable = false)
    @JsonIgnore
    @JsonIgnoreProperties("doors")
    private HubWorld hubWorld;

    @Column(name = "hub_world_id")
    private Long hubWorldId;

    @PostLoad
    public void postLoad(){
        if(hubWorld != null){
            hubWorldId = hubWorld.getId();
        }
    }
}
