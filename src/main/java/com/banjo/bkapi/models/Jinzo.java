package com.banjo.bkapi.models;

import com.banjo.bkapi.enums.Color;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="jinzos")
public class Jinzo  extends BaseEntity{

    //add color in string for insertion in db
    @Enumerated(EnumType.STRING)
    @Column(name="color")
    private Color color;

    @Lob
    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(
            name="world_id",
            foreignKey = @ForeignKey(name = "fk_world_id")
    )
    @JsonBackReference
    private World world;
}
