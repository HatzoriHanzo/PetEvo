package com.example.petevopierre.Activities.bean;

import java.io.Serializable;

import mobi.stos.podataka_lib.annotations.Column;
import mobi.stos.podataka_lib.annotations.Entity;
import mobi.stos.podataka_lib.annotations.PrimaryKey;
@Entity
public class Usuario implements Serializable {

    @PrimaryKey(autoIncrement = false)
    private int idlojista;

    @Column
    private String nomeLojista;

    @Column
    private int idloja;

    @Column
    private String email;


    public String getNomeLojista() {
        return nomeLojista;
    }

    public void setNomeLojista(String nomeLojista) {
        this.nomeLojista = nomeLojista;
    }

    public int getIdlojista() {
        return idlojista;
    }

    public void setIdlojista(int idlojista) {
        this.idlojista = idlojista;
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
