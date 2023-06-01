package com.adam.buzas.onlab.main.services;

import com.adam.buzas.onlab.main.model.Felhasznalo;
import com.adam.buzas.onlab.main.repository.FelhasznaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FelhasznaloService implements UserDetailsService {
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    public Felhasznalo getFelhasznalo(int id){
        return felhasznaloRepository.findById(id).get();
    }
    public String getFelhasznaloNev(String nev){
        if(!felhasznaloRepository.findByFelhasznaloNev(nev).isEmpty()){
            return felhasznaloRepository.findByFelhasznaloNev(nev).get().getFelhasznaloNev();
        }
        else{
            return "";
        }

    }
    public String getFelhasznaloEmail(String email){
        if(!felhasznaloRepository.findByEmail(email).isEmpty()){
            return felhasznaloRepository.findByEmail(email).get().getEmail();
        }
        else {
            return "";
        }

    }

    public void ujFelhasznalo(Felhasznalo f){
        felhasznaloRepository.save(f);
    }

    @Override
    public UserDetails loadUserByUsername(String felhasznaloNev) throws UsernameNotFoundException {
        Felhasznalo felhasznalo = felhasznaloRepository.findByFelhasznaloNev(felhasznaloNev).get();

        if (felhasznalo == null) {
            throw new UsernameNotFoundException("Érvénytelen felhasználónév vagy jelszó");
        }

        return User.builder()
                .username(felhasznalo.getFelhasznaloNev())
                .password(felhasznalo.getJelszo())
                .roles(felhasznalo.getSzerep())
                .build();
    }

    public Felhasznalo getFelhasznaloByFelhasznalonev(String fnev) {
        return felhasznaloRepository.findByFelhasznaloNev(fnev).get();
    }
}
