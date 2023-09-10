package com.edisolt.progress.servingwebcontent.service;

import com.edisolt.progress.servingwebcontent.entity.Note;
import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.entity.Workspace;
import com.edisolt.progress.servingwebcontent.ropository.NoteRepository;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import com.edisolt.progress.servingwebcontent.ropository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class NoteService {
    @Autowired
    public NoteRepository noteRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;

    public Workspace addNewNoteToDbToWorkspace (Workspace workspace, Note note) {
        Workspace managedWorkspace = workspaceRepository.findById(workspace.getId()).orElseThrow();
        managedWorkspace.addNote(note);

        workspaceRepository.save(managedWorkspace);

        return managedWorkspace;
    }
    public Workspace updateNoteToDbToWorkspace (Workspace workspace, Note note
            , String newText, String newTag) {
        Workspace managedWorkspace = workspaceRepository.findById(workspace.getId()).orElseThrow();
        note.setText(newText);
        note.setTag(newTag);
        noteRepository.save(note);

        return managedWorkspace;
    }
    public Workspace deleteNoteFromWorkspaceFromDb (Workspace workspace, Note note) {
        Workspace managedWorkspace = workspaceRepository.findById(workspace.getId()).orElseThrow();
        if (!isThereNoteInWorkspace(workspace, note)) throw new NoSuchElementException(
                "User does not contain that workspace!");
        managedWorkspace.getNotes().remove(note);
        noteRepository.delete(note);
        return managedWorkspace;
    }
    public Note findNoteFromDbByWorkspace (Workspace workspace, Note note) {
        if (!isThereNoteInWorkspace(workspace, note)) throw new NoSuchElementException(
                "User does not contain that workspace!");
        return note;
    }

    private boolean isThereNoteInWorkspace (Workspace workspace, Note note) {
        workspace = workspaceRepository.findById(workspace.getId()).orElseThrow();
        note = noteRepository.findById(note.getId()).orElseThrow();
        for (Note x : workspace.getNotes()) {
            if (x.getId() == note.getId()) {
                return true;
            }
        }
        return false;
    }
}
