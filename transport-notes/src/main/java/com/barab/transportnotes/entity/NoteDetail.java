package com.barab.transportnotes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "note_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    @Column(nullable = false)
    private Double quantity;

    private String comment;

}
