package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.repositories.AppelOffreRepository;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.AppelOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppelOffreServiceIMP extends CrudServiceIMP<AppelOffre,Integer> implements AppelOffreService {
    @Autowired
    AppelOffreRepository appelOffreRepository;
    @Autowired
    OffreProduitRepository offreProduitRepository;
    @Override
    public void affecterAppleOffreAOffreProduit(Integer idA, Integer idO) {
        AppelOffre appelOffre = appelOffreRepository.findById(idA).orElse(null);
        OffreProduit offreProduit = offreProduitRepository.findById(idO).orElse(null);
        appelOffre.setOffreProduits((List<OffreProduit>) offreProduit);
        appelOffreRepository.save(appelOffre);
    }

    @Override
    public void desaffecterAppeloffreNatureArticle( Integer idA) {

        AppelOffre appelOffre =appelOffreRepository.findById(idA).orElse(null);

        appelOffre.setNatureArticle(null);
        appelOffreRepository.save(appelOffre);

    }


}
