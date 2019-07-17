package com.yanxue.entity;

import java.io.Serializable;
import java.util.Date;

public class Lecture implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1522112469523320393L;
    private String id;
    private String name;
    private String professor;
    private Date time;
    private String address;
    private String status;
    private String activity;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lecture [id=" + id + ", name=" + name + ", professor=" + professor + ", time=" + time + ", address="
                + address + ", status=" + status + ", activity=" + activity + "]";
    }


}
