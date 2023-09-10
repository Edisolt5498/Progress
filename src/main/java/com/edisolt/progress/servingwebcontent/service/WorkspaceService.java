package com.edisolt.progress.servingwebcontent.service;

import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.entity.Workspace;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import com.edisolt.progress.servingwebcontent.ropository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

/**
 * argument 'User' should be @AuthenticationPrincipal
 */

@Service
public class WorkspaceService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;



    public User addNewWorkspaceToDbToUser (User user, Workspace workspace) {
        User managedUser = userRepository.findById(user.getId()).orElseThrow();

        Workspace newWorkSpace = new Workspace(workspace.getName(), managedUser);
        workspaceRepository.save(newWorkSpace);

        return managedUser;
    }

    public User updateWorkspaceToDbToUser (User user, Workspace workspace, String newWorkspaceName) {
        User managedUser = userRepository.findById(user.getId()).orElseThrow();
        workspace.setName(newWorkspaceName);
        workspaceRepository.save(workspace);

        return managedUser;
    }

    public User deleteWorkspaceFromUserFromDb (User user, Workspace workspace) {
        User managedUser = userRepository.findById(user.getId()).orElseThrow();

        if(!this.isThereWorkspaceInUser(managedUser, workspace)) throw new NoSuchElementException(
                "User does not contain that workspace!");
        managedUser.getWorkspaces().remove(workspace);
        workspaceRepository.delete(workspace);
        return managedUser;
    }

    public Workspace findWorkspaceFromDbByUser (User user, Workspace workspace) {

        if (!isThereWorkspaceInUser(user, workspace)) throw new NoSuchElementException(
                "User does not contain that workspace!");
        return workspace;
    }
    
    private boolean isThereWorkspaceInUser (User user, Workspace workspace) {
        user = userRepository.findById(user.getId()).orElseThrow();
        workspace = workspaceRepository.findById(workspace.getId()).orElseThrow();
        for (Workspace x : user.getWorkspaces()) {
            if (x.getId() == workspace.getId()) {
                return true;
            }
        }
        return false;
    }
}
