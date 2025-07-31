package com.arturFerreira.cinema.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class CinemaRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @OneToMany(mappedBy = "room")
    private Set<Session> sessions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "room_display_formats",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<DisplayFormat> types;

    public CinemaRoom() {
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<DisplayFormat> getTypes() {
        return types;
    }

    public void setTypes(Set<DisplayFormat> types) {
        this.types = types;
    }

    public void addType(DisplayFormat type) {
        this.types.add(type);
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
