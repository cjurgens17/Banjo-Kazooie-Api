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

    @JoinColumn(name = "world_Id")
    @ManyToOne
    private World world;

    @ManyToOne
    @JoinColumn(name = "hub_World_Id")
    private HubWorld hubWorld;
}
