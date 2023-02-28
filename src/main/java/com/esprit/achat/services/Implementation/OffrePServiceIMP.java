package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.repositories.NatureArticleRepository;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.repositories.UnitéRepository;
import com.esprit.achat.services.Interface.OffrePService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffrePServiceIMP extends CrudServiceIMP<OffreProduit,Integer> implements OffrePService {


    @Autowired
    OffreProduitRepository offreProduitRepository;

    @Override
    public List<OffreProduit> listeDeproduitParNature(NatureArticle natureArticle){
        return offreProduitRepository.produitParNature(natureArticle);

    }


    /*
    @Autowired
    NatureArticleRepository natureArticleRepository;
    @Autowired
    UnitéRepository unitéRepository;
    @Autowired
    OffreProduitRepository offreProduitRepository;

    public void affecterUnitesAuxNaturesArticles() {
        List<NatureArticle> natureArticles = natureArticleRepository.findAll();
        for (NatureArticle natureArticle : natureArticles) {
            String unite = obtenirUnitePourNatureArticle(natureArticle);
            natureArticle.setUnité(unite);
        }
        natureArticleRepository.saveAll(natureArticles);
    }

    public String obtenirUnitePourNatureArticle(NatureArticle natureArticle) {
        // Logique métier pour obtenir l'unité à affecter à la nature d'article
        if (natureArticle.getSecteur().equals("Fruits")) {
            return "kg";
        } else if (natureArticle.getSecteur().equals("Produits laitiers")) {
            return "L";
        } else {
            return "unité";
        }
    }

     */


}