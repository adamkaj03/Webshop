package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Konyv;
import com.adam.buzas.onlab.main.model.Rendeles;
import com.adam.buzas.onlab.main.model.RendeltKonyv;
import com.adam.buzas.onlab.main.repository.RendeltKonyvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RendeltKonyvService {

    @Autowired
    private RendeltKonyvRepository rendeltKonyvRepository;

    public Optional<RendeltKonyv> getRendeltKonyv(int id){
        return rendeltKonyvRepository.findById(id);
    }

    public void ujRendeltKonyv(Rendeles rendeles, Konyv konyv, int db){
        RendeltKonyv rendeltKonyv = new RendeltKonyv(rendeles, konyv, db);
        rendeltKonyvRepository.save(rendeltKonyv);
    }
}
