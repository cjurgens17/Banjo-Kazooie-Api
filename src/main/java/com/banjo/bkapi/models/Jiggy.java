package com.banjo.bkapi.models;


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

    @ManyToOne
    @JoinColumn(name = "worldId")
    private World world;

    //have to add hubworld at a later point
}