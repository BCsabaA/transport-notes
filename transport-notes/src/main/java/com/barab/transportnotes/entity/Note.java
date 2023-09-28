package com.barab.transportnotes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "notes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_address_id")
    private Address source;

    @ManyToOne
    @JoinColumn(name = "destination_address_id")
    private Address destination;

    @ManyToOne
    @JoinColumn(name = "transport_status_id")
    private TransportStatus transportStatus;

    private String comment;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<NoteDetail> noteDetails;





}
