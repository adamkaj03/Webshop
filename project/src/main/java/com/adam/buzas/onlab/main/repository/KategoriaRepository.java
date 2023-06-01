package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Kategoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriaRepository extends CrudRepository<Kategoria, Integer> {
}
