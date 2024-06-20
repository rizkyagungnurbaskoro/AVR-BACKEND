package com.csc3402.lab.avr.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_num")
    private Integer roomNum;

    @Column(name = "room_type")
    private String roomtype;

    @Column(name = "nights")
    private Integer nights;

    @Column(name = "desc")
    private String desc;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<Customer> customers;

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Room() {}

    public Room(Integer roomNum, Integer nights, String desc) {
        this.roomNum = roomNum;
        this.nights = nights;
        this.desc = desc;
    }

    // Getters and Setters
    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public Integer getNights() {
        return nights;
    }

    public void setNights(Integer nights) {
        this.nights = nights;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNum=" + roomNum +
                ", roomtype='" + roomtype + '\'' +
                ", nights=" + nights +
                ", desc='" + desc + '\'' +
                ", customers=" + customers +
                '}';
    }
}
