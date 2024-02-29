package com.banjo.bkapi.models;


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

    @JoinColumn(name = "worldId")
    @OneToMany
    private World world;


    //add
//    private HubWorld hubWorld;
}
