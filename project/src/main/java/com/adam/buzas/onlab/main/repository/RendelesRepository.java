package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Rendeles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendelesRepository extends CrudRepository<Rendeles, Integer> {
}
