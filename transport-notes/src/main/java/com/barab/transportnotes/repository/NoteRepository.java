package com.barab.transportnotes.repository;

import com.barab.transportnotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
