package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "notifications")

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer sender_id;

    @Column
    private Integer receiver_id;

    @Column
    private String message;

    @Column
    private Integer match_id;

    @Override
    public String toString() {
        return "Notification [id= " + id + ", sender_id= " +sender_id + ", receiver_id= " + receiver_id + ", message=" + message + ", match_id= "
                + match_id + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSender(Integer id) {
        this.sender_id = id;
    }

    public void setReceiver(Integer id) {
        this.receiver_id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSender() {
        return sender_id;
    }

    public Integer getReceiver() {
        return receiver_id;
    }

    public String getMessage(){
            return message;
    }

    public Integer getMatch(){
        return match_id;
    }

}
