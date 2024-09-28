package com.example.meetingschedulersystem.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public class BaseDao {

    protected Long created;
    protected Long updated;

    @PrePersist
    protected void onCreate() {
        this.created = System.currentTimeMillis();
        this.updated = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = System.currentTimeMillis();
    }

}
