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
@Table(name = "door")
public class Door  extends BaseEntity {

    @Lob
    @Column(name = "location")
    private String location;

    @Column(name = "required_notes")
    private int requiredNotes;

    @ManyToOne
    @JoinColumn(name = "hub_world_id")
    private HubWorld hubWorld;
}
