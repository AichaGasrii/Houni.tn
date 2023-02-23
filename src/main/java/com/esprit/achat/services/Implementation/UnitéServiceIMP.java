package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.persistence.entity.Unité;
import com.esprit.achat.repositories.NatureArticleRepository;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.UnitéService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitéServiceIMP extends CrudServiceIMP<Unité,Integer> implements UnitéService {
    /*
    @Autowired
    OffreProduitRepository offreProduitRepository;
    @Autowired
    NatureArticleRepository natureArticleRepository;

    public void affecterUnitesAuxCategories() {

        List<OffreProduit> offreProduitList = offreProduitRepository.findAll();
        for (NatureArticle natureArticle : natureArticles) {
            String unite = obtenirUnitePourCategorie(natureArticle);
            natureArticle.setUnite(unite);
        }
        natureArticleRepository.saveAll(natureArticles);
    }

    private String obtenirUnitePourCategorie(Categorie categorie) {
        // Logique métier pour obtenir l'unité à affecter à la catégorie
        if (categorie.getNom().equals("Fruits")) {
            return "kg";
        } else if (categorie.getNom().equals("Produits laitiers")) {
            return "L";
        } else {
            return "unité";
        }
    }
}

     */
}
