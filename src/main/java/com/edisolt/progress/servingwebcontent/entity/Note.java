package com.edisolt.progress.servingwebcontent.entity;

import com.edisolt.progress.servingwebcontent.additional.Time;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private String tag;
    private long startTime;
    private long endTime;
    private long duration;
    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;*/

    public Note(String text, String tag, long startTime, long endTime, long duration) {
        this.text = text;
        this.tag = tag;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String getDuration () {
        return Time.formatDuration(duration);
    }
}
