/*
package com.edisolt.progress.servingwebcontent.Controller.filter;

import com.edisolt.progress.servingwebcontent.entity.Note;
import com.edisolt.progress.servingwebcontent.ropository.NoteRepository;
import org.springframework.stereotype.Component;

@Component
public class MainFilterForNote {
    private final NoteRepository noteRepository;
    public MainFilterForNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Iterable<Note> filterByTag (String tag) {
        Iterable<Note> notes;
        if (tag==null || tag.isEmpty()) notes = noteRepository.findAll();
        else notes = noteRepository.findByTag(tag);
        return notes;
    }
}
*/
