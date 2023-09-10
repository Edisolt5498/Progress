package com.edisolt.progress.servingwebcontent.controller;

import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.entity.Workspace;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import com.edisolt.progress.servingwebcontent.ropository.WorkspaceRepository;
import com.edisolt.progress.servingwebcontent.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WorkspaceController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkspaceService workspaceService;

    //done/workspaces
    @GetMapping("/workspaces")
    public String showAllWorkspace (@AuthenticationPrincipal User user,
                                    Model model) {
        model.addAttribute("user"
                , userRepository.findByUsername(user.getUsername()).orElse(null));
        return "account";
    }

    //done/workspaces/{id}
    @GetMapping("/workspaces/{workspace}")
    public String showWorkspaceWithNotesById (@AuthenticationPrincipal User user,
                                              @PathVariable Workspace workspace,
                                              Model model) {
        model.addAttribute("workspace"
                , workspaceService.findWorkspaceFromDbByUser(user, workspace));

        return "workspace";
    }

    //done/workspaces/{id}/edit
    @GetMapping("/workspaces/{workspace}/edit/")
    public String editWorkspaceById (@AuthenticationPrincipal User user,
                                     @PathVariable Workspace workspace,
                                     Model model) {
        model.addAttribute("user"
                , workspaceService.findWorkspaceFromDbByUser(user, workspace));
        return "edit-workspace";
    }

    //done/workspaces
    @PostMapping("/workspaces")
    public String createNewWorkspace(@AuthenticationPrincipal User user,
                                     @RequestParam String workspaceName,
                                     Model model) {
        model.addAttribute("user"
                , workspaceService.addNewWorkspaceToDbToUser(user, new Workspace(workspaceName)));

        return "account";
    }

    //done/workspaces/{id}
    @DeleteMapping("/workspaces/{workspace}")
    public String deleteWorkspace(@AuthenticationPrincipal User user,
                         @PathVariable Workspace workspace,
                         Model model) {

        model.addAttribute("user"
                , workspaceService.deleteWorkspaceFromUserFromDb(user, workspace));

        return "redirect:/workspaces";
    }

    //done/workspaces/{id}
    @PutMapping("/workspaces/{workspace}")
    public String updateWorkspace(@AuthenticationPrincipal User user,
                      @PathVariable Workspace workspace,
                      @RequestParam String newWorkspaceName,
                      Model model) {
        model.addAttribute("user"
                , workspaceService.updateWorkspaceToDbToUser(user, workspace, newWorkspaceName));
        return "edit-workspace";
    }
    //////////////////////////////////////////////edit//////////////////////////////////////////////

}
