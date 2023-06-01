package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.RendeltKonyv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendeltKonyvRepository extends CrudRepository<RendeltKonyv, Integer> {
}
