package com.rbat.model;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "player")
public class Player implements Serializable {

    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "player_sequence";

    @NotBlank(message = "Player name is mandatory.")
    private String name;
    private boolean playing;
    private Date lastPlayingTime;

    @Transient
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getLastPlayingTime() {
        return lastPlayingTime;
    }

    public void setLastPlayingTime(Date lastPlayingTime) {
        this.lastPlayingTime = lastPlayingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playing=" + playing +
                '}';
    }
}
