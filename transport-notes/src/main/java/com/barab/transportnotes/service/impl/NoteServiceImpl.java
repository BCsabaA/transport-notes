package com.barab.transportnotes.service.impl;

import com.barab.transportnotes.dto.NoteDto;
import com.barab.transportnotes.repository.NoteRepository;
import com.barab.transportnotes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Override
    public void saveNote(NoteDto noteDto) {
        //TODO: NoteDto->Note
        //TODO: status->findByName
        //noteRepository.save(note);
    }
}
