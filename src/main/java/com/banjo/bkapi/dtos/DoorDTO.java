package com.banjo.bkapi.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoorDTO {
    private Long id;
    private String location;
    private int requiredNotes;
    private Long hub_world_id;
}
