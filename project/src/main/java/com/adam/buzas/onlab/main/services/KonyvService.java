package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Kategoria;
import com.adam.buzas.onlab.main.model.Konyv;
import com.adam.buzas.onlab.main.repository.KonyvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KonyvService {

    @Autowired
    KonyvRepository konyvRepository;

    public Optional<Konyv> getKonyv(int id){
        return konyvRepository.findById(id);
    }
    public List<Konyv> getKonyvOsszes(){
        List<Konyv> lista = new ArrayList<>();
        for(Konyv k : konyvRepository.findAll()){
            lista.add(k);
        }
        return lista;
    }

    public List<Konyv> getKonyvKategoria(Kategoria kategoria){
        List<Konyv> lista = new ArrayList<>();
        for(Konyv k : konyvRepository.findKonyvByKategoria(kategoria)){
            lista.add(k);
        }
        return lista;
    }

    public void ujKonyv(Konyv konyv){
        konyvRepository.save(konyv);
    }

    private List<Konyv> konyvekKeres(Iterable<Konyv> list, String szo){
        List<Konyv> keresettKonyvek = new ArrayList<>();

        for (Konyv konyv : list) {
            if (konyv.getCim().toLowerCase().startsWith(szo.toLowerCase())) {
                keresettKonyvek.add(konyv);
            }
        }

        return keresettKonyvek;
    }

    public List<Konyv> getKeresettek(String keres) {
        return konyvekKeres(konyvRepository.findAll(), keres);
    }

    public List<Konyv> getKonyvKategoriaEsKeres(Kategoria kategoria, String keres) {
        return konyvekKeres(konyvRepository.findKonyvByKategoria(kategoria), keres);
    }
}
