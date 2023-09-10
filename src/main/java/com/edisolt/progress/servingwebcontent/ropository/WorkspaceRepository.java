package com.edisolt.progress.servingwebcontent.ropository;

import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
    Optional<Workspace> findByName(String name);
}
