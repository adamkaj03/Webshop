package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Felhasznalo;
import com.adam.buzas.onlab.main.model.Rendeles;
import com.adam.buzas.onlab.main.repository.RendelesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RendelesService {

    @Autowired
    RendelesRepository rendelesRepository;

    public Optional<Rendeles> getRendeles(int id){
        return rendelesRepository.findById(id);
    }

    public int ujRendeles(LocalDateTime datum, String cim, Felhasznalo felhasznalo){
        Rendeles r = new Rendeles(datum, cim, felhasznalo);
        rendelesRepository.save(r);
        return r.getId();
    }
}
