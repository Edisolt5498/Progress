package com.edisolt.progress.servingwebcontent.controller;

import com.edisolt.progress.servingwebcontent.additional.Time;
import com.edisolt.progress.servingwebcontent.entity.Note;
import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.entity.Workspace;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import com.edisolt.progress.servingwebcontent.ropository.WorkspaceRepository;
import com.edisolt.progress.servingwebcontent.service.NoteService;
import com.edisolt.progress.servingwebcontent.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping("/workspaces/")
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    WorkspaceService workspaceService;

    //done/workspaces/{id}/notes
    @GetMapping("{workspace}/notes")
    public String showAllNotesByWorkspace () {
        return "redirect:/workspaces/{workspace}";
    }

    //done/workspaces/{id}/notes/{id}
    @GetMapping("{workspace}/notes/{note}")
    public String editNoteById (@AuthenticationPrincipal User user,
                                     @PathVariable Workspace workspace,
                                     @PathVariable Note note,
                                     Model model) {
        workspaceService.findWorkspaceFromDbByUser(user, workspace);//Throwable
        model.addAttribute("user"
                , noteService.findNoteFromDbByWorkspace(workspace, note));
        return "edit-note";
    }

    //done/workspaces/{id}/notes
    @PostMapping("{workspace}/notes/")
    public String addNewNote(@AuthenticationPrincipal User user,
                             @PathVariable Workspace workspace,
                             @RequestParam String tag,
                             @RequestParam String text,
                             @RequestParam("startTime") long startTimeMillis,
                             @RequestParam("endTime") long endTimeMillis,
                             @RequestParam("duration") long durationMillis,
                             Model model) {

        workspaceService.findWorkspaceFromDbByUser(user, workspace);//Throwable

        model.addAttribute("workspace"
                , noteService.addNewNoteToDbToWorkspace(workspace
                        , new Note(tag, text, startTimeMillis, endTimeMillis, durationMillis)));

        return "workspace";
    }

    //done/workspaces/{id}/notes/{id}
    @DeleteMapping ("{workspace}/notes/{note}")
    public String delete(@AuthenticationPrincipal User user,
                         @PathVariable Note note,
                         @PathVariable Workspace workspace,
                         Model model) {
        workspaceService.findWorkspaceFromDbByUser(user, workspace);//Throwable

        model.addAttribute("workspace"
                , noteService.deleteNoteFromWorkspaceFromDb(workspace, note)
        );

        return "redirect:/workspaces/{workspace}";
    }

    //done/workspaces/{id}/notes/{id}
    @PutMapping("{workspace}/notes/{note}")
    public String updateNote(@AuthenticationPrincipal User user,
                             @PathVariable Workspace workspace,
                             @RequestParam String newNoteText,
                             @RequestParam String newNoteTag,
                             @PathVariable Note note,
                             Model model) {
        workspaceService.findWorkspaceFromDbByUser(user, workspace);//Throwable
        model.addAttribute("user"
                , noteService.updateNoteToDbToWorkspace(workspace, note, newNoteText, newNoteTag));
        return "edit-note";
    }
}
