package com.arturFerreira.cinema.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "display_formats")
public class DisplayFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<CinemaRoom> rooms;

    public DisplayFormat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public enum Values {
        TWOD(1L, "2D"),
        THREED(2L, "3D"),
        IMAX(3L, "IMAX"),
        XD(4L, "XD"),
        DBOX(5L, "D-BOX");


        final Long displayID;
        final String name;

        Values(Long displayID, String name) {
            this.displayID = displayID;
            this.name = name;
        }

        public Long getDisplayID() {
            return displayID;
        }

        public String getName() {
            return name;
        }
    }
}
