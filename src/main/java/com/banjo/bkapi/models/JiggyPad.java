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
@Table(name = "jiggyPad")
public class JiggyPad extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(name = "word_id")
    private World world;

    @ManyToOne
    @JoinColumn(name = "hub_world_id")
    private HubWorld hubWorld;
}
