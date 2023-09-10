package com.edisolt.progress.servingwebcontent.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
            , fetch = FetchType.EAGER)
    @JoinTable( name = "user_workspace"
            , joinColumns = @JoinColumn(name = "workspace_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
    public void addUser (User user) {
        if (users == null) users = new ArrayList<>();
        users.add(user);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_id")
    private List<Note> notes;
    public void addNote (Note note) {
        if (notes == null) notes = new ArrayList<>();
        notes.add(note);
    }












    public Workspace(String name, User user) {
        this.name = name;
        this.addUser(user);
    }

    public Workspace(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}