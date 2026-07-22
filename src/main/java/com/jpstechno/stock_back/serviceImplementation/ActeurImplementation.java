package com.jpstechno.stock_back.serviceImplementation;

import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.modeles.Acteurs;
import com.jpstechno.stock_back.repositories.ActeurReposotories;
import com.jpstechno.stock_back.services.ActeurService;

@Service
public class ActeurImplementation implements ActeurService {

    private final ActeurReposotories acteurRepo;

    public ActeurImplementation(ActeurReposotories acteurRepo) {
        this.acteurRepo = acteurRepo;

    }

    private Acteurs findActeurById(Long id) {
        return acteurRepo.findById(id).orElseThrow(() -> new RuntimeException("Acteur introuvable"));
    }

}
