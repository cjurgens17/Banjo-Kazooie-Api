package com.banjo.bkapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="characters")
public class BKCharacter extends BaseEntity {

    @Column(name="name")
    private String name;

    @Lob
    @Column(name="description")
    private String description;

}
