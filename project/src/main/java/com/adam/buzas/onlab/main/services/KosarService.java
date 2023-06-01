package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Konyv;
import com.adam.buzas.onlab.main.model.Kosar;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class KosarService {


    private int osszegUjraSzamolas(Kosar kosar){
        int ujOsszeg = 0;
        for(Konyv k: kosar.getKosarTartalom()){
            ujOsszeg += k.getAr();
        }
        return ujOsszeg;
    }

    public void ujElemKosarba(Konyv konyv, Kosar kosar){
        kosar.getKosarTartalom().add(konyv);
        kosar.setOsszeg(osszegUjraSzamolas(kosar));
    }

    public void elemTorlesKosarbol(Konyv k, Kosar kosar){
        Iterator<Konyv> iterator = kosar.getKosarTartalom().iterator();
        while (iterator.hasNext()) {
            Konyv konyv = iterator.next();
            if (k.getId() == konyv.getId()) {
                iterator.remove();
                break;
            }
        }
        kosar.setOsszeg(osszegUjraSzamolas(kosar));
    }

}
