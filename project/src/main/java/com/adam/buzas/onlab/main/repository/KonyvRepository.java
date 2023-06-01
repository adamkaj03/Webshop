package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Kategoria;
import com.adam.buzas.onlab.main.model.Konyv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KonyvRepository extends CrudRepository<Konyv, Integer> {
    public Iterable<Konyv> findKonyvByKategoria(Kategoria kategoria);
}
