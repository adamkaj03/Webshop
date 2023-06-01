package com.adam.buzas.onlab.main.model;

import java.util.ArrayList;
import java.util.List;

public class Kosar {
    private List<Konyv> kosarTartalom = new ArrayList<>();

    private int osszeg;

    public Kosar() {
    }

    public List<Konyv> getKosarTartalom() {
        return kosarTartalom;
    }

    public void setKosarTartalom(List<Konyv> kosarTartalom) {
        this.kosarTartalom = kosarTartalom;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }
}
