package com.adam.buzas.onlab.main.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Rendeles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime idopont;
    private String szallitasiCim;
    @ManyToOne
    private Felhasznalo felhasznalo;

    public Rendeles() {
    }

    public Rendeles(LocalDateTime idopont, String szallitasiCim, Felhasznalo felhasznalo) {
        this.idopont = idopont;
        this.szallitasiCim = szallitasiCim;
        this.felhasznalo = felhasznalo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getIdopont() {
        return idopont;
    }

    public void setIdopont(LocalDateTime idopont) {
        this.idopont = idopont;
    }

    public String getSzallitasiCim() {
        return szallitasiCim;
    }

    public void setSzallitasiCim(String szallitasiCim) {
        this.szallitasiCim = szallitasiCim;
    }

    public Felhasznalo getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(Felhasznalo felhasznalo) {
        this.felhasznalo = felhasznalo;
    }
}
