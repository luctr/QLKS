package com.codegym.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Room.class)
    private Room room;

    public Orders() {
    }

    public Orders(LocalDateTime timeIn, LocalDateTime timeOut, User user, Room room) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.user = user;
        this.room = room;
    }
    public Orders(User user, Room room) {
        this.timeIn = LocalDateTime.now();
        this.user = user;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
