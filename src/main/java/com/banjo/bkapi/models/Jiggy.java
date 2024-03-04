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

    @ManyToOne
    @JoinColumn(
            name = "world_id",
            foreignKey = @ForeignKey(name = "fk_world_id")
    )
    @JsonBackReference
    private World world;

    @ManyToOne
    @JoinColumn(
            name = "hub_world_id",
            foreignKey = @ForeignKey(name = "fk_hub_world_id")
    )
    @JsonBackReference
    private HubWorld hubWorld;
}
