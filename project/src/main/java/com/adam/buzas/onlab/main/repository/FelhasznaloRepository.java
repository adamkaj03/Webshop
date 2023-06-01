package com.adam.buzas.onlab.main.repository;

import com.adam.buzas.onlab.main.model.Felhasznalo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FelhasznaloRepository extends CrudRepository<Felhasznalo, Integer> {
    Optional<Felhasznalo> findByEmail(String email);
    Optional<Felhasznalo> findByFelhasznaloNev(String nev);
}
