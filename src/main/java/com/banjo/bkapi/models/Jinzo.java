package com.banjo.bkapi.models;


import com.banjo.bkapi.enums.Color;
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

    @Column(name="color")
    private Color color;

    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(name="worldId")
    private World world;
}
