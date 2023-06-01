package com.adam.buzas.onlab.main.model;

import jakarta.persistence.*;

@Entity
public class Konyv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cim;
    private String szerzo;
    private int kiadasEve;
    private int ar;
    @ManyToOne
    private Kategoria kategoria;

    public Konyv() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public int getKiadasEve() {
        return kiadasEve;
    }

    public void setKiadasEve(int kiadasEve) {
        this.kiadasEve = kiadasEve;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }
}
