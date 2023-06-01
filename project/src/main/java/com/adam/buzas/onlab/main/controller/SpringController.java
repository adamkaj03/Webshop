package com.adam.buzas.onlab.main.controller;

import com.adam.buzas.onlab.main.model.Felhasznalo;
import com.adam.buzas.onlab.main.model.Kategoria;
import com.adam.buzas.onlab.main.model.Konyv;
import com.adam.buzas.onlab.main.model.Kosar;
import com.adam.buzas.onlab.main.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Controller
public class SpringController {

    @Autowired
    private FelhasznaloService felhasznaloService;
    @Autowired
    private RendelesService rendelesService;
    @Autowired
    private KategoriaService kategoriaService;
    @Autowired
    private KonyvService konyvService;
    @Autowired
    private RendeltKonyvService rendeltKonyvService;
    @Autowired
    private KosarService kosarService;
    @GetMapping("/")
    public String getIndexHtml(Model model, HttpSession session){
        if(session.getAttribute("szuresiKategoria") == null &&
                (session.getAttribute("keresoSzo") == "" ||
                session.getAttribute("keresoSzo") == null)){
            model.addAttribute("konyvek", konyvService.getKonyvOsszes());
        }
        else if(session.getAttribute("szuresiKategoria") != null &&
                (session.getAttribute("keresoSzo") == "" ||
                session.getAttribute("keresoSzo") == null)){
            int id = (Integer) session.getAttribute("szuresiKategoria");
            Kategoria kategoria = kategoriaService.getKategoria(id).get();
            model.addAttribute("konyvek", konyvService.getKonyvKategoria(kategoria));
        }
        else if(session.getAttribute("szuresiKategoria") == null &&
                session.getAttribute("keresoSzo") != ""){
            String keres = (String) session.getAttribute("keresoSzo");
            model.addAttribute("keresoSzo", keres);
            model.addAttribute("konyvek", konyvService.getKeresettek(keres));
        }
        else{
            String keres = (String) session.getAttribute("keresoSzo");
            int id = (Integer) session.getAttribute("szuresiKategoria");
            Kategoria kategoria = kategoriaService.getKategoria(id).get();
            model.addAttribute("keresoSzo", keres);
            model.addAttribute("konyvek", konyvService.getKonyvKategoriaEsKeres(kategoria, keres));
        }
        model.addAttribute("kategoriak", kategoriaService.getOsszes());

        if(session.getAttribute("kosar")==null){
            session.setAttribute("kosar", new Kosar());
        }
        return "index";
    }

    @GetMapping("/kijelentkezes")
    public String getKijelentkezesHtml(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/bejelentkezes")
    public String getBejelentkezesHtml(HttpSession session){
        return "bejelentkezes";
    }
    
    @GetMapping("/konyv")
    public String getKonyvHtml(Model model, @RequestParam int id){
        model.addAttribute("konyv", konyvService.getKonyv(id).get());
        return "konyv";
    }
    
    @GetMapping("/kosar")
    public String getKosarHtml(HttpSession session){
        return "kosar";
    }

    @GetMapping("/szallitas")
    public String getSzallitasHTML(){return "szallitas";}

    @GetMapping("/szallitasCimMegadas")
    public String szallitas(@RequestParam("iranyitoSzam") String iranyitoSzam,
                            @RequestParam("helyseg") String helyseg,
                            @RequestParam("utca") String utca,
                            HttpSession session) {
        String szallitasiAdatok = iranyitoSzam + ", " + helyseg + ", " + utca;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("szallitasiAdatok", szallitasiAdatok);
        return "redirect:penztar";
    }

    @GetMapping("/penztar")
    public String getPenztarHtml(HttpSession session){
        return "penztar";
    }

    @PostMapping("/rendelesLeadas")
    public String rendelesLeadas(HttpSession session, RedirectAttributes redirectAttributes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String cim = (String) session.getAttribute("szallitasiAdatok");
        int rendelesId = rendelesService.ujRendeles(LocalDateTime.now(), cim, felhasznaloService.getFelhasznaloByFelhasznalonev(auth.getName()));

        Kosar k = (Kosar) session.getAttribute("kosar");
        Map<Integer, Integer> konvyIdDb = new HashMap<>();
        for(int i=0; i<k.getKosarTartalom().size(); i++){
            int db = 1;
            for(int j=0; j<k.getKosarTartalom().size(); j++){
                if(k.getKosarTartalom().get(i).getId()==k.getKosarTartalom().get(j).getId() && i!=j){
                    db++;
                }
            }
            if(!konvyIdDb.containsKey(k.getKosarTartalom().get(i).getId())){
                konvyIdDb.put(k.getKosarTartalom().get(i).getId(), db);
            }
        }

        for (Map.Entry<Integer, Integer> entry : konvyIdDb.entrySet()) {
            rendeltKonyvService.ujRendeltKonyv(rendelesService.getRendeles(rendelesId).get(),
                    konyvService.getKonyv(entry.getKey()).get(),
                    entry.getValue());
        }





        session.removeAttribute("kosar");
        session.removeAttribute("kereses");
        session.removeAttribute("szuresiKategoria");
        session.removeAttribute("szallitasiAdatok");
        redirectAttributes.addFlashAttribute("rendelesEredmeny", "Köszönjük a rendelését!");

        return "redirect:/";
    }
    
    @GetMapping("/regisztracio")
    public String getRegisztracioHtml(Model model){
        model.addAttribute("felhasznalo", new Felhasznalo());
        return "regisztracio";
    }

    @PostMapping("/kosarba")
    public String postKosar(@RequestParam int id, HttpSession session){
        Konyv konyv = konyvService.getKonyv(id).get();
        Kosar kosar = (Kosar) session.getAttribute("kosar");
        kosarService.ujElemKosarba(konyv, kosar);
        session.setAttribute("kosar", kosar);
        return "redirect:/kosar";
    }

    @PostMapping("/kosarbolTorol")
    public String deleteKosarbol(@RequestParam int id, HttpSession session){
        Konyv konyv = konyvService.getKonyv(id).get();
        Kosar kosar = (Kosar) session.getAttribute("kosar");
        kosarService.elemTorlesKosarbol(konyv, kosar);
        session.setAttribute("kosar", kosar);
        return "redirect:/kosar";
    }

    @GetMapping("/kategoriaSzures")
    public String kategoriaSzur(HttpSession session, Integer kategoria){
        session.setAttribute("szuresiKategoria", kategoria);
        return "redirect:/";
    }

    @GetMapping("/kereses")
    public String kereses(Model model, HttpSession session, @RequestParam("keresoSzo") String kereses) {
        session.setAttribute("keresoSzo", kereses);
        return "redirect:/";
    }




    @GetMapping("/ujFelhasznalo")
    public String ujFelhasznaloRegisztral(Felhasznalo f, RedirectAttributes redirectAttributes){
        if(felhasznaloService.getFelhasznaloNev(f.getFelhasznaloNev()) == "" &&
            felhasznaloService.getFelhasznaloEmail(f.getEmail()) == "")   {
            f.setSzerep("USER");
            felhasznaloService.ujFelhasznalo(f);
        }
        else if(felhasznaloService.getFelhasznaloNev(f.getFelhasznaloNev()) != ""){
            redirectAttributes.addFlashAttribute("regisztacioUzenet", "Van a rendszerben ilyen felhasználónévvel vagy email címmel felhasználó!");
        }
        else{
            redirectAttributes.addFlashAttribute("regisztacioUzenet", "Van a rendszerben ilyen felhasználónévvel vagy email címmel felhasználó!");
        }
        return "redirect:/";
    }

    @GetMapping("/elemFelvetel")
    public String elemFelvetelMenu(Model model){
        model.addAttribute("ujKonyv", new Konyv());
        model.addAttribute("kategoriak", kategoriaService.getOsszes());
        return "elemFelvetel";
    }

    @GetMapping("/ujKonyv")
    public String ujKonyv(@ModelAttribute("ujKonyv") Konyv k, Integer kategoria){
        Kategoria ujKonyvKategoriaja = kategoriaService.getKategoria(kategoria).get();
        if(ujKonyvKategoriaja != null){
            System.out.println(kategoria);
            System.out.println(k.getCim());
            System.out.println(ujKonyvKategoriaja.getNev());
        }

        k.setKategoria(ujKonyvKategoriaja);
        konyvService.ujKonyv(k);
        return "redirect:/";
    }

    @GetMapping("/ujKategoria")
    public String ujKategoria(@RequestParam("kategoriaFel") String kategoria){
        if(!kategoria.equals(""))
            kategoriaService.ujKategoria(kategoria);
        return "redirect:/elemFelvetel";
    }


}

