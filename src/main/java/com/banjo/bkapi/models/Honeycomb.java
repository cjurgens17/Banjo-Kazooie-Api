package com.banjo.bkapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JoinColumn(
            name = "world_id",
            foreignKey = @ForeignKey(name = "fk_world_id")
    )
    @ManyToOne
    @JsonBackReference
    private World world;

    @ManyToOne
    @JoinColumn(
            name = "hub_World_id",
            foreignKey = @ForeignKey(name = "fk_hub_world_id")
    )
    @JsonBackReference
    private HubWorld hubWorld;
}
