package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Felhasznalo;
import com.adam.buzas.onlab.main.model.Kategoria;
import com.adam.buzas.onlab.main.repository.KategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KategoriaService {
    @Autowired
    private KategoriaRepository kategoriaRepositoryRepository;

    public Optional<Kategoria> getKategoria(int id){
        return kategoriaRepositoryRepository.findById(id);
    }

    public Iterable<Kategoria> getOsszes(){
        return kategoriaRepositoryRepository.findAll();
    }

    public void ujKategoria(String kategoriaNev){
        Kategoria kategoria = new Kategoria();
        kategoria.setNev(kategoriaNev);
        kategoriaRepositoryRepository.save(kategoria);
    }
}
