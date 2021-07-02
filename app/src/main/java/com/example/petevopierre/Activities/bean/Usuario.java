package com.example.petevopierre.Activities.bean;

import java.io.Serializable;

import mobi.stos.podataka_lib.annotations.Column;
import mobi.stos.podataka_lib.annotations.ForeignKey;
import mobi.stos.podataka_lib.annotations.PrimaryKey;

public class Usuario implements Serializable {
    @PrimaryKey(autoIncrement = false)
    private int id;

    @Column
    private int idloja;

    @Column
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdloja() {
        return idloja;
    }

    public void setIdloja(int idloja) {
        this.idloja = idloja;
    }
}
