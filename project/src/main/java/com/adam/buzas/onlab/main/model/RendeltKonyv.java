package com.adam.buzas.onlab.main.model;

import jakarta.persistence.*;

@Entity
public class RendeltKonyv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Rendeles rendeles;
    @ManyToOne
    private Konyv konyv;
    private int db;

    public RendeltKonyv(Rendeles rendeles, Konyv konyv, int db) {
        this.rendeles = rendeles;
        this.konyv = konyv;
        this.db = db;
    }

    public Konyv getKonyv() {
        return konyv;
    }

    public void setKonyv(Konyv rendeltKonyv) {
        this.konyv = rendeltKonyv;
    }

    public RendeltKonyv() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rendeles getRendeles() {
        return rendeles;
    }

    public void setRendeles(Rendeles rendeles) {
        this.rendeles = rendeles;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }
}
