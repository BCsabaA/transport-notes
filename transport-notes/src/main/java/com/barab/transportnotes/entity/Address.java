package com.barab.transportnotes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "addresses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;
    private String comment;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Note> sources;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Note> destinations;

}
