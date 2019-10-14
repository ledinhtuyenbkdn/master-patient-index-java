package com.ledinhtuyenbkdn.masterpersonindex.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mpiKey;

    private String mpiValue;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMpiKey() {
        return mpiKey;
    }

    public void setMpiKey(String mpiKey) {
        this.mpiKey = mpiKey;
    }

    public String getMpiValue() {
        return mpiValue;
    }

    public void setMpiValue(String mpiValue) {
        this.mpiValue = mpiValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
