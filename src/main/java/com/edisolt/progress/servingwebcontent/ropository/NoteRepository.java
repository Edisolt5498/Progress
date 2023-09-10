package com.edisolt.progress.servingwebcontent.ropository;

import com.edisolt.progress.servingwebcontent.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    Iterable<Note> findByTag(String tag);
}
